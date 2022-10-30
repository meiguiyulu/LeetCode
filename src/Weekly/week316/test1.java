package Weekly.week316;

/**
 * @Author LiuYunJie
 * @Date 2022/10/30 14:59
 **/
public class test1 {

    /**
     * 2446. 判断两个事件是否存在冲突
     *
     * @param event1
     * @param event2
     * @return
     */
    public boolean haveConflict(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[1]) <= 0 && event1[1].compareTo(event2[0]) >= 0;
    }

}
