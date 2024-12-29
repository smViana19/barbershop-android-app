package br.com.samuel.barbershopapplication.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.samuel.barbershopapplication.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CustomDialog(
  state: DialogState
) {
  if (state.open) {
    Dialog(onDismissRequest = state.onDismiss) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .shadow(2.dp, shape = RoundedCornerShape(8.dp))
          .background(Color.White, shape = RoundedCornerShape(8.dp))
          .clip(RoundedCornerShape(12.dp)),
      ) {
        Column(
          modifier = Modifier
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          when (state.type) {
            DialogType.SUCCESS -> LottieAnimation(
              modifier = Modifier.size(100.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success_animation)).value
            )

            DialogType.ERROR -> LottieAnimation(
              modifier = Modifier.size(72.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_animation)).value
            )

            DialogType.ALERT -> LottieAnimation(
              modifier = Modifier.size(72.dp),
              composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alert_animation)).value
            )
            else -> {}
          }
          Spacer(modifier = Modifier.height(8.dp))
          state.title?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(8.dp))
          }
          state.msg?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(16.dp))
          }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
          ) {
            state.dismissButton?.let {
              TextButton(onClick = state.onDismiss) {}
              Spacer(modifier = Modifier.width(8.dp))
            }
          }
          Button(
            onClick = state.onConfirm,
          ) {
            Text(state.confirmButton)
          }
        }
      }
    }
  }
}

@Preview
@Composable
private fun CustomDialogPreview() {
  CustomDialog(
    state = DialogState(
      type = DialogType.SUCCESS,
      title = "Sucesso",
      msg = "Logado com sucesso"
    )
  )
}