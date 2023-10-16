/*-
 * =================================LICENSE_START==================================
 * scimark2-lambda
 * ====================================SECTION=====================================
 * Copyright (C) 2022 - 2023 Andy Boothe
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
package com.sigpwned.jnt.scimark2;

import java.io.PrintStream;

/**
 * SciMark2: A Java numerical benchmark measuring performance of computational kernels for FFTs,
 * Monte Carlo simulation, sparse matrix computations, Jacobi SOR, and dense LU matrix
 * factorizations.
 * 
 * 
 */


public class commandline {
  public static final int NUM_BENCHMARKS = 5;

  public static final int BENCHMARK_FFT_INDEX = 1;

  public static final int BENCHMARK_SOR_INDEX = 2;

  public static final int BENCHMARK_MONTE_CARLO_INDEX = 3;

  public static final int BENCHMARK_SPARSE_MATMULT_INDEX = 4;

  public static final int BENCHMARK_LU_INDEX = 5;

  public static final int BENCHMARK_SIZE = NUM_BENCHMARKS + 1;

  public static final int BENCHMARK_COMPOSITE_INDEX = 0;

  /*
   * Benchmark 5 kernels with individual Mflops. "results[0]" has the average Mflop rate.
   * 
   */

  public static class Args {
    public static Args fromArgs(String[] args) {
      // default to the (small) cache-contained version
      double min_time = Constants.RESOLUTION_DEFAULT;

      int FFT_size = Constants.FFT_SIZE;
      int SOR_size = Constants.SOR_SIZE;
      int Sparse_size_M = Constants.SPARSE_SIZE_M;
      int Sparse_size_nz = Constants.SPARSE_SIZE_nz;
      int LU_size = Constants.LU_SIZE;

      // look for runtime options

      if (args.length > 0) {

        if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("-help")) {
          System.err.println("Usage: [-large] [minimum_time]");
          System.exit(0);
        }

        int current_arg = 0;
        if (args[current_arg].equalsIgnoreCase("-large")) {
          FFT_size = Constants.LG_FFT_SIZE;
          SOR_size = Constants.LG_SOR_SIZE;
          Sparse_size_M = Constants.LG_SPARSE_SIZE_M;
          Sparse_size_nz = Constants.LG_SPARSE_SIZE_nz;
          LU_size = Constants.LG_LU_SIZE;

          current_arg++;
        }

        if (args.length > current_arg)
          min_time = Double.valueOf(args[current_arg]).doubleValue();
      }

      return new Args(min_time, FFT_size, SOR_size, Sparse_size_M, Sparse_size_nz, LU_size);
    }

    public final double min_time;
    public final int FFT_size;
    public final int SOR_size;
    public final int Sparse_size_M;
    public final int Sparse_size_nz;
    public final int LU_size;

    public Args(double min_time, int FFT_size, int SOR_size, int Sparse_size_M, int Sparse_size_nz,
        int LU_size) {
      this.min_time = min_time;
      this.FFT_size = FFT_size;
      this.SOR_size = SOR_size;
      this.Sparse_size_M = Sparse_size_M;
      this.Sparse_size_nz = Sparse_size_nz;
      this.LU_size = LU_size;
    }
  }


  public static void main(String cliargs[]) {
    // Parse our CLI arguments
    Args args = Args.fromArgs(cliargs);

    // run the benchmark
    double res[] = benchmark(args);

    // print out results
    printReport(System.out, args, res);
  }

  public static double[] benchmark(Args args) {
    double res[] = new double[BENCHMARK_SIZE];
    Random R = new Random(Constants.RANDOM_SEED);

    res[BENCHMARK_FFT_INDEX] = kernel.measureFFT(args.FFT_size, args.min_time, R);
    res[BENCHMARK_SOR_INDEX] = kernel.measureSOR(args.SOR_size, args.min_time, R);
    res[BENCHMARK_MONTE_CARLO_INDEX] = kernel.measureMonteCarlo(args.min_time, R);
    res[BENCHMARK_SPARSE_MATMULT_INDEX] =
        kernel.measureSparseMatmult(args.Sparse_size_M, args.Sparse_size_nz, args.min_time, R);
    res[BENCHMARK_LU_INDEX] = kernel.measureLU(args.LU_size, args.min_time, R);

    res[0] = (res[BENCHMARK_FFT_INDEX] + res[BENCHMARK_SOR_INDEX] + res[BENCHMARK_MONTE_CARLO_INDEX]
        + res[BENCHMARK_SPARSE_MATMULT_INDEX] + res[BENCHMARK_LU_INDEX]) / NUM_BENCHMARKS;

    return res;
  }

  public static double[] overall(double[][] ress) {
    double[] res = new double[BENCHMARK_SIZE];

    // add up all the measurements from every result
    for (int i = 0; i < ress.length; i++) {
      double[] resi = ress[i];
      for (int j = 0; j < res.length; j++)
        res[j] += resi[j];
    }

    // divide all measurements by number of data points
    for (int j = 0; j < BENCHMARK_SIZE; j++)
      res[j] = res[j] / ress.length;

    return res;
  }

  public static void printReport(PrintStream out, Args args, double[] res) {
    out.println();
    out.println("SciMark 2.0a");
    out.println();
    out.println("Composite Score: " + res[BENCHMARK_COMPOSITE_INDEX]);
    out.print("FFT (" + args.FFT_size + "): ");
    if (res[BENCHMARK_FFT_INDEX] == 0.0)
      out.println(" ERROR, INVALID NUMERICAL RESULT!");
    else
      out.println(res[BENCHMARK_FFT_INDEX]);

    out.println(
        "SOR (" + args.SOR_size + "x" + args.SOR_size + "): " + "  " + res[BENCHMARK_SOR_INDEX]);
    out.println("Monte Carlo : " + res[BENCHMARK_MONTE_CARLO_INDEX]);
    out.println("Sparse matmult (N=" + args.Sparse_size_M + ", nz=" + args.Sparse_size_nz + "): "
        + res[BENCHMARK_SPARSE_MATMULT_INDEX]);
    out.print("LU (" + args.LU_size + "x" + args.LU_size + "): ");
    if (res[BENCHMARK_LU_INDEX] == 0.0)
      out.println(" ERROR, INVALID NUMERICAL RESULT!");
    else
      out.println(res[BENCHMARK_LU_INDEX]);

    // print out System info
    out.println();
    out.println("java.vendor: " + System.getProperty("java.vendor"));
    out.println("java.version: " + System.getProperty("java.version"));
    out.println("os.arch: " + System.getProperty("os.arch"));
    out.println("os.name: " + System.getProperty("os.name"));
    out.println("os.version: " + System.getProperty("os.version"));
  }
}
