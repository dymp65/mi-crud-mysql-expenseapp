package com.praktisi.expenseappmysql.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.praktisi.expenseappmysql.Models.Note;
import com.praktisi.expenseappmysql.Helpers.DatabaseHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteHelper {
    String url = DatabaseHelper.getUrl();
    String user = DatabaseHelper.getUser();
    String password = DatabaseHelper.getPassword();

    public void addNoteAsync(Note note, DatabaseHelper.DatabaseCallback<Boolean> callback) {
        new AsyncTask<Note, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                return addNote(notes[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                callback.onComplete(result);
            }
        }.execute(note);
    }

    private boolean addNote(Note note) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO notes (title, content) VALUES (?, ?)")) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error adding note", e);
            return false;
        }
    }

    public void getNotesAsync(DatabaseHelper.DatabaseCallback<List<Note>> callback) {
        new AsyncTask<Void, Void, List<Note>>() {
            @Override
            protected List<Note> doInBackground(Void... voids) {
                return getNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                callback.onComplete(notes);
            }
        }.execute();
    }

    private List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM notes");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                notes.add(new Note(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                ));
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error fetching notes", e);
        }
        return notes;
    }

    public void updateNoteAsync(Note note, DatabaseHelper.DatabaseCallback<Boolean> callback) {
        new AsyncTask<Note, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                return updateNote(notes[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                callback.onComplete(result);
            }
        }.execute(note);
    }

    private boolean updateNote(Note note) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE id = ?")) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error updating note", e);
            return false;
        }
    }

    public void deleteNoteAsync(int id, DatabaseHelper.DatabaseCallback<Boolean> callback) {
        new AsyncTask<Integer, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Integer... ids) {
                return deleteNote(ids[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                callback.onComplete(result);
            }
        }.execute(id);
    }

    private boolean deleteNote(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM notes WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error deleting note", e);
            return false;
        }
    }
}
