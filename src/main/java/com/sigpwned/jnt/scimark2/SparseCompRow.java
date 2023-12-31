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

public class SparseCompRow
{
	/* multiple iterations used to make kernel have roughly
		same granulairty as other Scimark kernels. */

	public static double num_flops(int N, int nz, int num_iterations)
	{
		/* Note that if nz does not divide N evenly, then the
		   actual number of nonzeros used is adjusted slightly.
		*/
		int actual_nz = (nz/N) * N;
		return ((double)actual_nz) * 2.0 * ((double) num_iterations);
	}


	/* computes  a matrix-vector multiply with a sparse matrix
		held in compress-row format.  If the size of the matrix
		in MxN with nz nonzeros, then the val[] is the nz nonzeros,
		with its ith entry in column col[i].  The integer vector row[]
		is of size M+1 and row[i] points to the begining of the
		ith row in col[].  
	*/

	public static void matmult( double y[], double val[], int row[],
		int col[], double x[], int NUM_ITERATIONS)
	{
		int M = row.length - 1;

		for (int reps=0; reps<NUM_ITERATIONS; reps++)
		{
		
			for (int r=0; r<M; r++)
			{
				double sum = 0.0; 
				int rowR = row[r];
				int rowRp1 = row[r+1];
				for (int i=rowR; i<rowRp1; i++)
					sum += x[ col[i] ] * val[i];
				y[r] = sum;
			}
		}
	}

}
