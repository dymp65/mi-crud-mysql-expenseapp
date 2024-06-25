package com.praktisi.expenseappmysql.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.praktisi.expenseappmysql.Models.Expense;
import com.praktisi.expenseappmysql.Models.Note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://10.0.2.2:3306/praktisi";
    private static final String USER = "myuser";
    private static final String PASSWORD = "rootDB";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }


    public interface DatabaseCallback<T> {
        void onComplete(T result);
    }

//    public void getExpensesAsync(DatabaseCallback<List<Expense>> callback) {
//        new AsyncTask<Void, Void, List<Expense>>() {
//            @Override
//            protected List<Expense> doInBackground(Void... voids) {
//                return getExpenses();
//            }
//
//            @Override
//            protected void onPostExecute(List<Expense> expenses) {
//                callback.onComplete(expenses);
//            }
//        }.execute();
//    }

    public void addExpenseAsync(Expense expense, DatabaseCallback<Boolean> callback) {
        new AsyncTask<Expense, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Expense... expenses) {
                return addExpense(expenses[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                callback.onComplete(result);
            }
        }.execute(expense);
    }

    private boolean addExpense(Expense expense) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO expenses (user_id, type_id, date, title, desc, amount) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(2, expense.getUser_id());
            statement.setInt(2, expense.getType_id());
            statement.setString(2, expense.getDate());
            statement.setString(1, expense.getTitle());
            statement.setString(2, expense.getDesc());
            statement.setDouble(2, expense.getAmount());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error adding expense", e);
            return false;
        }
    }
}
