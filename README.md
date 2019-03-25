Manejo inalámbirco de encendido de luces con SPRING:

Este es el código de una nueva versión del proyecto para la gestión de luces para una vivienda. De momento solo enciende un LED en un pin GPIO, pero sobre el cual se puede conectar un relé para controlar el encendido de las luces de una vivienda.

El objetivo de la realización de este proyecto es primeramente renovar mis conocimientos  de J2EE y aprender Spring MVC, Security, Spring JPA DATA, Spring REST y administración de sistemas LINUX. En segundo lugar es programar para mi hermano el proyecto y poder controlar el encendido de las luces exteriores de nuestra finca desde el móvil. Siempre con idea de ampliarlo y mejorarlo.

Esta versión tiene mejoras sobre la gestión de luces que realice hace un par de meses con J2EE y el API REST ya que esta ocasión está escrita íntegramente con J2SE/J2EE/Spring en el servidor. 
E igualmente corrige algunos problemas de seguridad que había en el servidor. Como por ejemplo el hecho de asignarles permisos de superadmin al tomcat y no haber encriptado las claves de la base de datos.

Además he creado una parte back para añadir cuentas de usuario y configurar la red wifi que genera    la aplicación en la Raspberry PI ·la cual es accesible a través de la dirección: http://192.168.5.6/lights/back/index.html

Si se analiza el código, he dejado cosas en el tintero como por ejemplo haber empezado y no completado el i18n, que la web sea responsive o las tareas programadas.

Pero la intención como he dicho era aprender Spring.

La imagen de la Raspberry PI 3 se puede descargar en: https://drive.google.com/open?id=1DiEJZBW3uaqKh3c4FvzCZE4KynFesFlf

