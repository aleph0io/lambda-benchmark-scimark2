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

public class Constants
{

	public static final double RESOLUTION_DEFAULT = 2.0;  /*secs*/
	public static final int RANDOM_SEED = 101010;

	// default: small (cache-contained) problem sizes
	//
	public static final int FFT_SIZE = 1024;  // must be a power of two
	public static final int SOR_SIZE =100; // NxN grid
	public static final int SPARSE_SIZE_M = 1000;
	public static final int SPARSE_SIZE_nz = 5000;
	public static final int LU_SIZE = 100;

	// large (out-of-cache) problem sizes
	//
	public static final int LG_FFT_SIZE = 1048576;  // must be a power of two
	public static final int LG_SOR_SIZE =1000; // NxN grid
	public static final int LG_SPARSE_SIZE_M = 100000;
	public static final int LG_SPARSE_SIZE_nz =1000000;
	public static final int LG_LU_SIZE = 1000;

	// tiny problem sizes (used to mainly to preload network classes 
	//                     for applet, so that network download times
	//                     are factored out of benchmark.)
	//
	public static final int TINY_FFT_SIZE = 16;  // must be a power of two
	public static final int TINY_SOR_SIZE =10; // NxN grid
	public static final int TINY_SPARSE_SIZE_M = 10;
	public static final int TINY_SPARSE_SIZE_N = 10;
	public static final int TINY_SPARSE_SIZE_nz = 50;
	public static final int TINY_LU_SIZE = 10;

}

