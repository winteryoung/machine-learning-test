package com.github.winteryoung.mltest

import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.RealMatrix

/**
 * @author Winter Young
 * @since 2015/12/27
 */
class WeightMatrix(var matrix: RealMatrix) {
    constructor(
            height: Int,
            width: Int,
            valueInitializer: () -> Double = { 0.0 }
    ) : this(createMatrix(height, width, valueInitializer))

    override fun toString(): String {
        return matrix.toString()
    }

    fun zero() = WeightMatrix(MatrixUtils.createRealMatrix(matrix.rowDimension, matrix.columnDimension))

    fun copyWithNeuronPerturbed(epsilon: Double, neuron: Int, previousLayerNeuron: Int): WeightMatrix {
        return WeightMatrix(matrix.copy().apply {
            setEntry(neuron, previousLayerNeuron, getEntry(neuron, previousLayerNeuron) + epsilon)
        })
    }

    fun copy() = WeightMatrix(matrix.copy())

    companion object {
        private fun createMatrix(height: Int, width: Int, valueInitializer: () -> Double): RealMatrix {
            val m = Array(height) {
                Array(width) {
                    valueInitializer()
                }.toDoubleArray()
            }
            return MatrixUtils.createRealMatrix(m)
        }
    }
}