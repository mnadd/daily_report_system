package constants;

public enum MessageConst {

    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),
    I_STARTED("出勤しました。"),
    I_FINISHED("退勤しました。"),
    I_APPROVED("承認しました。"),
    I_APPROVEFALSE("差し戻しました。"),

    E_NONAME("氏名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOEMP_CODE("社員番号を入力してください。"),
    E_EMP_CODE_EXIST("入力された社員番号の情報は既に存在しています。"),
    E_NOTITLE("タイトルを入力してください。"),
    E_NOCONTENT("内容を入力してください。"),
    E_START("出勤は既に押されています。"),
    E_FINISH("出勤が押されていません。"),
    E_NONTIME("時間を入力していません"),
    E_COMMENT("コメントを入力してください。");


    private final String text;

    private MessageConst(final String text) {
        this.text = text;
    }

    public String getMessage() {
        return this.text;
    }
}
