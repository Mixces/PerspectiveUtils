package me.mixces.perspectiveutils.utils

fun lerp (start: Float, stop: Float, amount: Float): Float {
  return start + (start - stop) * amount
}