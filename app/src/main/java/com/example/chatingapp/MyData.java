package com.example.chatingapp;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MyData {
    public String save(String sender,String msg)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/FA/save.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("sender", CHAR_SET) + "=" + URLEncoder.encode(sender, CHAR_SET);
            data += "&" + URLEncoder.encode("msg", CHAR_SET) + "=" + URLEncoder.encode(msg, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();
//------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //
            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
        }
        return response;
    }
    public String login()
    {
        final String SERVER_URL = "http://192.168.43.33:8080/FA/disp.jsp"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            Log.d("con string",""+connection);
            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            String data="";
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        } catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        } finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }
        Log.d("Data mil gya",response);
        return response;
    }
}

