#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <cuda.h>
using namespace std;

const int N = 1*1024;
const int threadsPerBlock = 256;
const int blocksPerGrid = ceil(n/2560);

// vector sum C = A + B
// Each thread performs one pair wise addition
__global__
void vecAddKernel(float* A, float* B, float* C, int n){
    int i = threadIdx.x + blockDim.x * blockIdx.x;
    if(i<n) C[i] = A[i] + B[i];
}

void vecAdd(float* A, float* B, float* C, int n){
    int size = n* sizeof(float);
    float *d_A, *d_B, *d_C;
    cudaMalloc((void **) &d_A, size);
    cudaMemcpy(d_A, A, size, cudaMemcpyHostToDevice);
    cudaMalloc((void **) &B_d, size);
    ccudaMemcpy(d_B, B, size, cudaMemcpyHostToDevice);
    cudaMalloc((void **) &d_C, size);
    vecAddKernel<<<blocksPerGrid, threadsPerBlock>>>(d_A, d_B, d_C, n);
    cudaMemcpy(C, d_C, size, cudaMemcpyDeviceToHost);
    
    // Free device memory for A, B, C
    cudaFree(d_Ad); cudaFree(d_B); cudaFree(d_C);
}

int main( void ) {
    cout << "Suma de vectores" << endl;
    float *h_A, *h_B, *h_C;  

    h_A = new float[N];
    h_B = new float[N];
    h_C = new float[N];

    for (int i=0; i<N; i++) {
        h_A[i] = i;
        h_B[i] = i+1;
    }

    vecAdd(h_A, h_B, h_C, N);

    for (int i=0; i<N; i++) {
        cout << h_C[i] << endl;
    }

    delete[] h_A;
    delete[] h_B;
    delete[] h_C;
}