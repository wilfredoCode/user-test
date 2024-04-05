# User
Microservicio construido con Spring Boot 3 y Java 17
# Ejecución del Proyecto
## Ejecución con Docker Desktop

1. Abre Docker Desktop y asegúrate de que la franja inferior izquierda esté en verde.

2. Abre la consola en la ruta raíz del proyecto (al mismo nivel que el archivo Dockerfile).

3. Ejecuta el siguiente comando. Esto descargará las imágenes necesarias para la ejecución del proyecto (imagen de Java 17 y Maven) y creará la imagen para poder ejecutarla:

    ```
    docker build -t user:latest .
    ```

4. Con la imagen construida, vamos a ejecutarla con el siguiente comando:

    ```
    docker run -p 3002:3002 user:latest
    ```

5. Cuando en la consola aparezca el mensaje "Started UserApplication", ya está listo para hacer las pruebas.

6. En la carpeta `collection-to-call-services`, se encuentra una colección de Postman para llamar al proyecto. Esta colección contiene ejemplos de respuestas para la petición de registro.

## Notas Importantes

1. En este proyecto se está utilizando una base de datos en memoria llamada H2, ya que es fácil de probar porque no es necesario instalar ningún motor de base de datos por fuera. Como es en memoria, es manejada por dentro de Spring Boot mediante la dependencia H2.

2. Al ser una base de datos en memoria, los datos solo estarán vigentes mientras esté ejecutando el microservicio. Esta dependencia H2 es utilizada solo en desarrollo para pruebas.

3. No es necesario ejecutar algún script para la creación de modelos en la base de datos, ya que está activo el `ddl-auto:update` esto hace que se generen automáticamente las tablas en la base datos.

4. Para acceder a la base de datos y revisar como están quedando los datos insertados se puede realizar un login en H2, para esto con el microservicio en ejecución ingresar a la siguiente url `http://localhost:3002/h2-ui` en la cual los datos para ingresar serán los siguientes, Driver Class: `org.h2.Driver` JDBC URL: `jdbc:h2:mem:users_example` User Name: `test` Password: `test`.