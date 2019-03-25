Manejo inal�mbirco de encendido de luces con SPRING:

Este es el c�digo de una nueva versi�n del proyecto para la gesti�n de luces para una vivienda. De momento solo enciende un LED en un pin GPIO, pero sobre el cual se puede conectar un rel� para controlar el encendido de las luces de una vivienda.

El objetivo de la realizaci�n de este proyecto es primeramente renovar mis conocimientos  de J2EE y aprender Spring MVC, Security, Spring JPA DATA, Spring REST y administraci�n de sistemas LINUX. En segundo lugar es programar para mi hermano el proyecto y poder controlar el encendido de las luces exteriores de nuestra finca desde el m�vil. Siempre con idea de ampliarlo y mejorarlo.

Esta versi�n tiene mejoras sobre la gesti�n de luces que realice hace un par de meses con J2EE y el API REST ya que esta ocasi�n est� escrita �ntegramente con J2SE/J2EE/Spring en el servidor. 
E igualmente corrige algunos problemas de seguridad que hab�a en el servidor. Como por ejemplo el hecho de asignarles permisos de superadmin al tomcat y no haber encriptado las claves de la base de datos.

Adem�s he creado una parte back para a�adir cuentas de usuario y configurar la red wifi que genera    la aplicaci�n en la Raspberry PI �la cual es accesible a trav�s de la direcci�n: http://192.168.5.6/lights/back/index.html

Si se analiza el c�digo, he dejado cosas en el tintero como por ejemplo haber empezado y no completado el i18n, que la web sea responsive o las tareas programadas.

Pero la intenci�n como he dicho era aprender Spring.

La imagen de la Raspberry PI 3 se puede descargar en: https://drive.google.com/open?id=1DiEJZBW3uaqKh3c4FvzCZE4KynFesFlf

