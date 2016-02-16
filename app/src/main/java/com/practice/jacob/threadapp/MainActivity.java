package com.practice.jacob.threadapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = "numbers.txt";
        file = new File(this.getFilesDir(), fileName);
    }

    public void createFile(View v) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))){
            for (int i = 1; i <= 10; i++) {
                String output = (String.valueOf(i)+ "\n");
                out.write(output);
            }
            out.close();
            Thread.sleep(250);
        } catch (InterruptedException ex){
            System.out.println("I caught this interrupted exception " + ex);
        }
        catch (Exception e) {
            System.out.println("I caught this exception " + e);
        }
    }

    public void loadFile(View v){
        ListView myList = (ListView)(findViewById(R.id.listView));
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(myAdapter);
        String line;

        try (BufferedReader in = new BufferedReader(new FileReader(file))){
            while ((line = in.readLine()) != null){
                myAdapter.add(line);
            }
            in.close();
            Thread.sleep(250);
        } catch(FileNotFoundException ex) {
            System.out.println("We couldn't find the file, sorry");
        } catch(IOException ex) {
            System.out.println("IO exception " + ex);
        } catch (InterruptedException ex){
            System.out.println("Your threads are messed up with this " + ex);
        }
    }

    public void clearList(View v){
        myAdapter.clear();
    }

    //private variables
    private String fileName;
    private File file;
    private ArrayAdapter myAdapter;
}
