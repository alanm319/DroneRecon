package com.dronerecon.ws;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.SecureRandom;

/**
 *
 * @author Your Name Here :-)
 */
public class DroneDataService extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // r
        // g
        // ##############################\
        String area_id = request.getParameter("area_id");
        int x = Integer.parseInt(request.getParameter("tilex"));
        int y = Integer.parseInt(request.getParameter("tiley"));
        int totalCols = Integer.parseInt(request.getParameter("totalcols"));
        int totalRows = Integer.parseInt(request.getParameter("totalrows"));
        String r = request.getParameter("r");
        String g = request.getParameter("g");




        //call cloud and pass data to be store in DB
        try {
            //call PortalDBService
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id="+area_id+"&tilex="+x+"&tiley="+y+"&r="+r+"&g="+g);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }






        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################
        String sDirection = "right";


        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################
        if(y % 2 == 0){                             //check if on even row, moving right
            if(x == totalCols-1){
                y++;
                sDirection= "left";
            }
            else{
                x++;
            }
        }

        else{                                       //odd row, moving left
            if(x == 0){
                y++;
                sDirection = "right";
            }
            else{
                x--;
            }
        }

        if(y == totalRows){                                  //check if drone is off grid
            sDirection = "stop";
        }




        // ##############################
        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################

        String sReturnJson = "{\"area_id\":\"" + area_id + "\", \"nextTileX\":\""+ x +"\", \"nextTileY\":\""+ y +"\", \"direction\":\""+ sDirection +"\"}";
        out.println(sReturnJson);

    }
}

