package Weekly.week296;

/**
 * @Author LiuYunJie
 * @Date 2022/6/11 8:44
 **/
public class TextEditor {

    private StringBuilder builder;
    private int index;

    public TextEditor() {
        builder = new StringBuilder();
        index = 0;
    }

    public void addText(String text) {
        builder.append(text);
        index += text.length();
    }

    public int deleteText(int k) {
        int num = Math.min(k, index);
        builder.delete(index - num, index);
        index -= num;
        return num;
    }

    public String cursorLeft(int k) {
        index -= Math.min(k, 10);
        index = Math.max(index, 0);
        String s = builder.toString();
        return s.substring(0, index);
    }

    public String cursorRight(int k) {
        index += k;
        index = Math.min(index, builder.length());
        String s = builder.toString();
        return s.substring(Math.max(0, index - 10), index);
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
        textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
        System.out.println(textEditor.deleteText(4)); // 返回 4
        textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
        System.out.println(textEditor.cursorRight(3)); // 返回 "etpractice"
        System.out.println(textEditor.cursorLeft(8)); // 返回 "leet"
        System.out.println(textEditor.deleteText(10)); // 返回 4
        System.out.println(textEditor.cursorLeft(2)); // 返回 ""
        System.out.println(textEditor.cursorRight(6)); // 返回 "practi"
    }

}
