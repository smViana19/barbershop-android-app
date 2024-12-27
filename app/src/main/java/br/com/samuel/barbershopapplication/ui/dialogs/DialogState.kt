package br.com.samuel.barbershopapplication.ui.dialogs

data class DialogState(
  val open: Boolean = false,
  val type: DialogType = DialogType.NONE,
  val title: String? = null,
  val msg: String? = null,
  val colors: () -> Unit = {},
  val confirmButton: String = "OK",
  val dismissButton: String? = null,
  val onConfirm: () -> Unit = {},
  val onDismiss: () -> Unit = {}
)

enum class DialogType {
  NONE,
  SUCCESS,
  ERROR,
  ALERT
}