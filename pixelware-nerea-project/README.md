# pixelware-nerea-project
Proyecto de BSD Varsity para Pixelware

Desde la consola para crear la base de datos local con HsqlDB
//java -cp target/pixelware-nerea-project-0.0.1-SNAPSHOT/WEB-INF/lib/hsqldb-2.2.4.jar org.hsqldb.server.Server --database.0 file:pixelwaredb --dbname.0 bbdd

*El redireccionamiento de los tres controladores (response.sendRedirect("/");) no funciona en local. está pensado para desplegar la aplicación
*En local habría que añadir "/pixelware-nerea-project" antes de cada ruta (response.sendRedirect("/pixelware-nerea-project/") );