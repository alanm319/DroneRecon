<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>




<html>
  <head>
    
      <%
	String areaID = request.getParameter("area_id");
	DBManager oDBManager = new DBManager();
        oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;

	
	
	AreaGridTile highestR = readAreaGridTiles(String areaID).get(0);
	AreaGridTile highestG =readAreaGridTiles(String areaID).get(0);

	for(AreaGridTile tile: readAreaGridTiles(String areaID)){

		if(tile.r > highestR){
			highestR = tile.r;
		}

		if(tile.g > highestG){
			highestG = tile.g;
		}
	}

	out.println("The tile with the highest number of red is: " + highestR.x + "," + highestR.y);
	out.println("The tile with the highest number of green is: " + highestG.x + "," + highestG.y);
	 

      %>

    

    

  </head>

  <body>

    
  </body>
</html>