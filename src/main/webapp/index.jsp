<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Piranha Start</title>
    </head>
    <body>
        <h1>Piranha Start</h1>
        <a href="start.xhtml">Start using Piranha</a>
        <%

            response.setStatus(302);
            response.setHeader("Location", "start.xhtml");
            response.flushBuffer();
         %>
    </body>
</html>
