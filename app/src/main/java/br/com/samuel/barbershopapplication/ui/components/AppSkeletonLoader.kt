package br.com.samuel.barbershopapplication.ui.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedSkeletonLoader() {
  val infiniteTransition = rememberInfiniteTransition(label = "")
  val alpha by infiniteTransition.animateFloat(
    initialValue = 0.3f,
    targetValue = 0.6f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = ""
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    repeat(5) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = alpha))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
          Box(
            modifier = Modifier
              .height(16.dp)
              .fillMaxWidth(0.6f)
              .background(Color.Gray.copy(alpha = alpha))
          )
          Spacer(modifier = Modifier.height(8.dp))
          Box(
            modifier = Modifier
              .height(12.dp)
              .fillMaxWidth(0.8f)
              .background(Color.Gray.copy(alpha = alpha))
          )
        }
      }
    }
  }
}

@Composable
fun RectangleSkeletonLoader(
  isLoading: Boolean,
  itemCount: Int = 5, // Número de retângulos
  itemHeight: Dp = 16.dp, // Altura de cada retângulo
  itemSpacing: Dp = 8.dp, // Espaçamento entre os retângulos
  modifier: Modifier = Modifier
) {
  if (isLoading) {
    Column(
      modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      repeat(itemCount) {
        SkeletonBox(
          modifier = Modifier.fillMaxWidth(),
          height = itemHeight
        )
        Spacer(modifier = Modifier.height(itemSpacing))
      }
    }
  }
}

@Composable
fun SkeletonBox(
  modifier: Modifier = Modifier,
  width: Dp = Dp.Unspecified,
  height: Dp = Dp.Unspecified,
  shape: androidx.compose.ui.graphics.Shape = RectangleShape,
  animated: Boolean = true
) {
  val baseModifier = modifier
    .then(if (width != Dp.Unspecified) Modifier.width(width) else Modifier)
    .then(if (height != Dp.Unspecified) Modifier.height(height) else Modifier)
    .clip(shape)
    .background(Color.Gray.copy(alpha = if (animated) 0.1f else 0.3f))

  if (animated) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
      initialValue = 0.3f,
      targetValue = 0.6f,
      animationSpec = infiniteRepeatable(
        animation = tween(durationMillis = 1000, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Reverse
      ),
      label = ""
    )
    Box(modifier = baseModifier.background(Color.Gray.copy(alpha = alpha)))
  } else {
    Box(modifier = baseModifier)
  }
}

@Composable
fun ListSkeletonLoader() {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp)) {
    repeat(10) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        SkeletonBox(
          width = 50.dp,
          height = 50.dp,
          shape = CircleShape
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
          SkeletonBox(height = 16.dp, width = 150.dp, shape = RectangleShape)
          Spacer(modifier = Modifier.height(8.dp))
          SkeletonBox(height = 12.dp, width = 200.dp, shape = RectangleShape)
        }
      }
    }
  }
}

@Composable
fun DetailSkeletonLoader(modifier: Modifier = Modifier) {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp)) {
    // Skeleton para título
    SkeletonBox(height = 24.dp, width = 200.dp, shape = RectangleShape)
    Spacer(modifier = Modifier.height(16.dp))
    // Skeleton para parágrafos
    repeat(3) {
      SkeletonBox(height = 16.dp, width = Dp.Unspecified, shape = RectangleShape)
      Spacer(modifier = Modifier.height(8.dp))
    }
    Spacer(modifier = Modifier.height(16.dp))
    // Skeleton para botão
    SkeletonBox(height = 48.dp, width = 120.dp, shape = RoundedCornerShape(8.dp))
  }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedSkeletonLoaderPreview() {
  AnimatedSkeletonLoader()
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenAnimatedSkeletonLoaderPreview() {
  ListSkeletonLoader()
}

@Preview(showBackground = true)
@Composable
private fun DetailSkeletonLoaderPreview() {
  DetailSkeletonLoader()
}

@Preview(showBackground = true)
@Composable
private fun RectangleSkeletonLoaderPreview() {
  RectangleSkeletonLoader(true)
}

@Preview(showBackground = true)
@Composable
private fun SkeletonBoxPreview() {
  SkeletonBox(width = 100.dp, height = 20.dp)
}