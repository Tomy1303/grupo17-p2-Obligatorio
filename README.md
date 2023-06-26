# grupo17-p2-Obligatorio


## Manual de uso

### En la clase Main se encuentra el método main, el cual se encarga de ejecutar el programa. 

### Al inicializarlo, nos encontramos con un menú de opciones, el cual nos permite elegir entre las siguientes opciones:

1. Obtener Top 10 Pilotos Activos, si se selecciona esta opción, se le va a solicitar que ingrese el mes del cual quiere obtener los 10 pilotos mas activos, y posteriormente se los muestra.
####
2. Obtener Top 15 Usuarios con más Tweets. En esta opción se muestran los top 15 de usuarios con más tweets.
####
3. Obtener Cantidad de Hashtags Distintos. Si se selecciona la opción 3, se le solicita que ingrese el dia, mes y año del cual quiere obtener la cantidad de hashtags distintos, y posteriormente se los muestra.
####
4. Obtener Hashtag Más Usado. En esta opción se le solicita que ingrese el dia, mes y año del cual quiere obtener el hashtag más usado, y posteriormente se lo muestra.
####
5. Obtener Top 7 Cuentas con más Favoritos. En esta opción se muestran los top 7 de cuentas con más favoritos.
####
6. Obtener Cantidad de Tweets con una Palabra. Si se selecciona esta opción, se le solicita que ingrese la palabra de la cual quiere obtener la cantidad de tweets que la contienen, y posteriormente se los muestra.
####
7. Carga de datos. En esta opcion se cargan los datos del archivo .csv que se encuentra en la carpeta "FilesReader" del proyecto.
####
0. Salir. Si se selecciona esta opción, el programa finaliza.

### Para seleccionar una opción, se debe ingresar el número correspondiente a la misma y presionar Enter.


## Descripcion de procesos de carga de datos

### 1. Carga de datos de usuarios

1. Para la carga de datos se instalo el paquete de apache commons csv.
2. En caso de que alguna linea contuviera un error, se captura la excepcion y se continua con la carga de datos.
3. Los valores null no generaran problemas
4. Se utilizo un hash para verificar si hay datos repetidos y si hay no se almacenan.

## Decisiones tomadas

1. Decidimos usar LocalDateTime para las fechas.
2. Si en la carga de datos se encuentra un usuario ya creado, no se crea nuevamente pero se actualizan sus favoritos.
3. Añadir una clase readers e importar  librerias apache para la carga de datos.

4. En tweeter impl las listas de tweets y users son listas enlazadas, ya que las recorremos constantemente y ademas, en el get linkedlist, agregamos un puntero que apunta al ultimo valor obtendo. Este servira para que cuando se recorra una lista de manera completa y continua se pueda acceder al siguiente valor con orden 1.


## Importante

### El archivo csv debe de colocarse "src/FilesReader/f1_dataset.csv" con esta ruta en caso contrario dara error.

