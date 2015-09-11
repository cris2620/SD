 
#include <pthread.h>  
#include <stdio.h>  
#include <stdlib.h>  
#include <string.h>  
#define MAX_THREADS     5
// tabla con los identificadores de los threads  
pthread_t tabla_thr[MAX_THREADS];  
// tipo de datos y tabla con los parametros   
typedef struct {  
  int id;  
  char *cadena;  
} thr_param_t;  
thr_param_t param[MAX_THREADS]; 
void *funcion_thr(thr_param_t *p)  
{    
  printf("%s %d\n", p->cadena, p->id);  
  pthread_exit(&(p->id));  
}  
int main(void)  
{  
  int i, *res;  
  // creamos los threads  
  printf("Creando threads...\n");  
  for (i=0; i<MAX_THREADS; i++) {  
    param[i].cadena = strdup("Thread presente ._.7");  
    param[i].id     = i;  
    pthread_create(&tabla_thr[i], NULL, (void *)&funcion_thr,   
     (void *)&param[i]);  
  }  
  // esperamos que terminen los threads  
  printf("Threads creados, ejecutando\n");  
  for (i=0; i<MAX_THREADS; i++) {  
    pthread_join(tabla_thr[i], (void *)&res);  
    printf("El thread %d devuelve el valor %d\n", i, *res);  
  }  
  printf("Todos los threads finalizados.!\n");  
  return 0;  
}  
/*** <CORTAR AQUI> ***/  
