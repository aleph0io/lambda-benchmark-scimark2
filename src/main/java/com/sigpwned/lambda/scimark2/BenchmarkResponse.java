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

import java.util.Objects;

public class BenchmarkResponse {
  private double minTime;
  private int threads;
  private int fftSize;
  private double fftMeasurement;
  private int sorSize;
  private double sorMeasurement;
  private double monteCarloMeasurement;
  private int sparseSizeM;
  private int sparseSizeNz;
  private double sparseMatmultMeasurement;
  private int luSize;
  private double luMeasurement;
  private double compositeMeasurement;
  private double totalMeasurement;

  /**
   * @return the minTime
   */
  public double getMinTime() {
    return minTime;
  }

  /**
   * @param minTime the minTime to set
   */
  public void setMinTime(double minTime) {
    this.minTime = minTime;
  }

  /**
   * @return the threads
   */
  public int getThreads() {
    return threads;
  }

  /**
   * @param threads the threads to set
   */
  public void setThreads(int threads) {
    this.threads = threads;
  }

  /**
   * @return the fftSize
   */
  public int getFftSize() {
    return fftSize;
  }

  /**
   * @param fftSize the fftSize to set
   */
  public void setFftSize(int fftSize) {
    this.fftSize = fftSize;
  }

  /**
   * @return the fftMeasurement
   */
  public double getFftMeasurement() {
    return fftMeasurement;
  }

  /**
   * @param fftMeasurement the fftMeasurement to set
   */
  public void setFftMeasurement(double fftMeasurement) {
    this.fftMeasurement = fftMeasurement;
  }

  /**
   * @return the sorSize
   */
  public int getSorSize() {
    return sorSize;
  }

  /**
   * @param sorSize the sorSize to set
   */
  public void setSorSize(int sorSize) {
    this.sorSize = sorSize;
  }

  /**
   * @return the sorMeasurement
   */
  public double getSorMeasurement() {
    return sorMeasurement;
  }

  /**
   * @param sorMeasurement the sorMeasurement to set
   */
  public void setSorMeasurement(double sorMeasurement) {
    this.sorMeasurement = sorMeasurement;
  }

  /**
   * @return the monteCarloMeasurement
   */
  public double getMonteCarloMeasurement() {
    return monteCarloMeasurement;
  }

  /**
   * @param monteCarloMeasurement the monteCarloMeasurement to set
   */
  public void setMonteCarloMeasurement(double monteCarloMeasurement) {
    this.monteCarloMeasurement = monteCarloMeasurement;
  }

  /**
   * @return the sparseSizeM
   */
  public int getSparseSizeM() {
    return sparseSizeM;
  }

  /**
   * @param sparseSizeM the sparseSizeM to set
   */
  public void setSparseSizeM(int sparseSizeM) {
    this.sparseSizeM = sparseSizeM;
  }

  /**
   * @return the sparseSizeNz
   */
  public int getSparseSizeNz() {
    return sparseSizeNz;
  }

  /**
   * @param sparseSizeNz the sparseSizeNz to set
   */
  public void setSparseSizeNz(int sparseSizeNz) {
    this.sparseSizeNz = sparseSizeNz;
  }

  /**
   * @return the sparseMatmultMeasurement
   */
  public double getSparseMatmultMeasurement() {
    return sparseMatmultMeasurement;
  }

  /**
   * @param sparseMatmultMeasurement the sparseMatmultMeasurement to set
   */
  public void setSparseMatmultMeasurement(double sparseMatmultMeasurement) {
    this.sparseMatmultMeasurement = sparseMatmultMeasurement;
  }

  /**
   * @return the luSize
   */
  public int getLuSize() {
    return luSize;
  }

  /**
   * @param luSize the luSize to set
   */
  public void setLuSize(int luSize) {
    this.luSize = luSize;
  }

  /**
   * @return the luMeasurement
   */
  public double getLuMeasurement() {
    return luMeasurement;
  }

  /**
   * @param luMeasurement the luMeasurement to set
   */
  public void setLuMeasurement(double luMeasurement) {
    this.luMeasurement = luMeasurement;
  }

  /**
   * @return the compositeMeasurement
   */
  public double getCompositeMeasurement() {
    return compositeMeasurement;
  }

  /**
   * @param compositeMeasurement the compositeMeasurement to set
   */
  public void setCompositeMeasurement(double compositeMeasurement) {
    this.compositeMeasurement = compositeMeasurement;
  }

  /**
   * @return the totalMeasurement
   */
  public double getTotalMeasurement() {
    return totalMeasurement;
  }

  /**
   * @param totalMeasurement the totalMeasurement to set
   */
  public void setTotalMeasurement(double totalMeasurement) {
    this.totalMeasurement = totalMeasurement;
  }

  @Override
  public int hashCode() {
    return Objects.hash(compositeMeasurement, fftMeasurement, fftSize, luMeasurement, luSize,
        minTime, monteCarloMeasurement, sorMeasurement, sorSize, sparseMatmultMeasurement,
        sparseSizeM, sparseSizeNz, threads, totalMeasurement);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BenchmarkResponse other = (BenchmarkResponse) obj;
    return Double.doubleToLongBits(compositeMeasurement) == Double
        .doubleToLongBits(other.compositeMeasurement)
        && Double.doubleToLongBits(fftMeasurement) == Double.doubleToLongBits(other.fftMeasurement)
        && fftSize == other.fftSize
        && Double.doubleToLongBits(luMeasurement) == Double.doubleToLongBits(other.luMeasurement)
        && luSize == other.luSize
        && Double.doubleToLongBits(minTime) == Double.doubleToLongBits(other.minTime)
        && Double.doubleToLongBits(monteCarloMeasurement) == Double
            .doubleToLongBits(other.monteCarloMeasurement)
        && Double.doubleToLongBits(sorMeasurement) == Double.doubleToLongBits(other.sorMeasurement)
        && sorSize == other.sorSize
        && Double.doubleToLongBits(sparseMatmultMeasurement) == Double
            .doubleToLongBits(other.sparseMatmultMeasurement)
        && sparseSizeM == other.sparseSizeM && sparseSizeNz == other.sparseSizeNz
        && threads == other.threads && Double.doubleToLongBits(totalMeasurement) == Double
            .doubleToLongBits(other.totalMeasurement);
  }

  @Override
  public String toString() {
    return "BenchmarkResponse [minTime=" + minTime + ", threads=" + threads + ", fftSize=" + fftSize
        + ", fftMeasurement=" + fftMeasurement + ", sorSize=" + sorSize + ", sorMeasurement="
        + sorMeasurement + ", monteCarloMeasurement=" + monteCarloMeasurement + ", sparseSizeM="
        + sparseSizeM + ", sparseSizeNz=" + sparseSizeNz + ", sparseMatmultMeasurement="
        + sparseMatmultMeasurement + ", luSize=" + luSize + ", luMeasurement=" + luMeasurement
        + ", compositeMeasurement=" + compositeMeasurement + ", totalMeasurement="
        + totalMeasurement + "]";
  }
}
