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

/**
 Estimate Pi by approximating the area of a circle.

 How: generate N random numbers in the unit square, (0,0) to (1,1)
 and see how are within a radius of 1 or less, i.e.
 <pre>  

 sqrt(x^2 + y^2) < r

 </pre>
  since the radius is 1.0, we can square both sides
  and avoid a sqrt() computation:
  <pre>

    x^2 + y^2 <= 1.0

  </pre>
  this area under the curve is (Pi * r^2)/ 4.0,
  and the area of the unit of square is 1.0,
  so Pi can be approximated by 
  <pre>
		        # points with x^2+y^2 < 1
     Pi =~ 		--------------------------  * 4.0
		             total # points

  </pre>

*/

public class MonteCarlo
{
	final static int SEED = 113;

	public static final double num_flops(int Num_samples)
	{
		// 3 flops in x^2+y^2 and 1 flop in random routine

		return ((double) Num_samples)* 4.0;

	}

	

	public static final double integrate(int Num_samples)
	{

		Random R = new Random(SEED);


		int under_curve = 0;
		for (int count=0; count<Num_samples; count++)
		{
			double x= R.nextDouble();
			double y= R.nextDouble();

			if ( x*x + y*y <= 1.0)
				 under_curve ++;
			
		}

		return ((double) under_curve / Num_samples) * 4.0;
	}


}
