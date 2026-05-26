package qust;

import neu.QinHuangDao;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 强仔
 * Date: 2025-04-10
 * Time: 16:52
 */
// 可以继承 QinHuangDao， 但无法继承 Test
public class QingDao{

    // 可以实例化 QinHuangDao，但无法实例化 Test
    QinHuangDao place = new QinHuangDao();
}
