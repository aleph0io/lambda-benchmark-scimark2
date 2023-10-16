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
package com.sigpwned.lambda.scimark2;

import java.io.InterruptedIOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sigpwned.jnt.scimark2.commandline;
import com.sigpwned.jnt.scimark2.commandline.Args;

public class LambdaFunction implements RequestHandler<BenchmarkRequest, BenchmarkResponse> {
  @Override
  public BenchmarkResponse handleRequest(BenchmarkRequest input, Context context) {
    final int availableProcessors = Runtime.getRuntime().availableProcessors();

    System.out.println(String.format("Detected available processors: %d", availableProcessors));

    final String[] mainargs = toBenchmarkMainArgs(input);

    System.out.println(String.format("Using benchmark CLI args: %s", Arrays.toString(mainargs)));

    final Args args = Args.fromArgs(mainargs);

    // Create our threads
    Thread[] threads = new Thread[availableProcessors];
    double[][] benchmarks = new double[availableProcessors][];
    for (int i = 0; i < availableProcessors; i++) {
      final int index = i;
      threads[index] = new Thread("benchmark-thread-" + index) {
        @Override
        public void run() {
          benchmarks[index] = commandline.benchmark(args);
        }
      };
    }

    // Start our threads
    for (Thread thread : threads)
      thread.start();

    // Join our threads
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.err.println("Interrupted.");
        Thread.currentThread().interrupt();
        throw new UncheckedIOException(new InterruptedIOException());
      }
    }

    // Combine our results
    double[] overall = commandline.overall(benchmarks);

    // Print out our results
    for (int i = 0; i < availableProcessors; i++) {
      System.out.println(String.format("==== BENCHMARK THREAD %d ====", i));
      double[] benchmarki = benchmarks[i];
      commandline.printReport(System.out, args, benchmarki);
      System.out.println();
    }

    System.out.println("==== BENCHMARK OVERALL ====");
    commandline.printReport(System.out, args, overall);
    System.out.println();

    // Return our result
    BenchmarkResponse result = new BenchmarkResponse();
    result.setFftMeasurement(overall[commandline.BENCHMARK_FFT_INDEX]);
    result.setFftSize(args.FFT_size);
    result.setLuMeasurement(overall[commandline.BENCHMARK_LU_INDEX]);
    result.setLuSize(args.LU_size);
    result.setMinTime(args.min_time);
    result.setMonteCarloMeasurement(overall[commandline.BENCHMARK_MONTE_CARLO_INDEX]);
    result.setSorMeasurement(overall[commandline.BENCHMARK_SOR_INDEX]);
    result.setSorSize(args.SOR_size);
    result.setSparseMatmultMeasurement(overall[commandline.BENCHMARK_SPARSE_MATMULT_INDEX]);
    result.setSparseSizeM(args.Sparse_size_M);
    result.setSparseSizeNz(args.Sparse_size_nz);
    result.setThreads(availableProcessors);
    result.setCompositeMeasurement(overall[commandline.BENCHMARK_COMPOSITE_INDEX]);
    result.setTotalMeasurement(overall[commandline.BENCHMARK_COMPOSITE_INDEX] * availableProcessors);

    return result;
  }

  public static String[] toBenchmarkMainArgs(BenchmarkRequest input) {
    List<String> mainargs = new ArrayList<>();
    if (input.getLarge())
      mainargs.add("-large");
    return mainargs.toArray(new String[0]);
  }
}
