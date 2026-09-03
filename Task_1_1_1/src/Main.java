//TIP Чтобы <b>запустить</b> код, нажмите <shortcut actionId="Run"/> или
// нажмите на значок <icon src="AllIcons.Actions.Execute"/> в поле.
void main() {
    //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, когда курсор находится на выделенном тексте
    // чтобы увидеть, как OpenIDE предлагает это исправить.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Нажмите <shortcut actionId="Debug"/>, чтобы начать отладку вашего кода. Мы установили одну <icon src="AllIcons.Debugger.Db_set_breakpoint"/> точку останова
        // для вас, но вы всегда можете добавить больше, нажав <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }
}
