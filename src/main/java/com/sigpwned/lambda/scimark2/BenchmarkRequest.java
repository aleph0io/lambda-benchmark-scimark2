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

public class BenchmarkRequest {
  private boolean large;

  public BenchmarkRequest() {}

  /**
   * @return the large
   */
  public boolean getLarge() {
    return large;
  }

  /**
   * @param large the large to set
   */
  public void setLarge(boolean large) {
    this.large = large;
  }

  @Override
  public int hashCode() {
    return Objects.hash(large);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BenchmarkRequest other = (BenchmarkRequest) obj;
    return large == other.large;
  }

  @Override
  public String toString() {
    return "BenchmarkRequest [large=" + large + "]";
  }
}
