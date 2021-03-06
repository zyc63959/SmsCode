自定义验证码规则帮助
--------

- 当默认的验证码解析规则不能正确解析某些特殊短信中的验证码时，就可以使用自定义规则。
- 自定义验证码规则包含三个部分：公司或组织名，验证码关键字，验证码正则表达式。

温馨提示：**只要上述 3 个部分所组成的规则能正确识别指定验证码短信即可，不一定要拘泥于格式**

例子：
- `请使用 192830 验证你的 Instagram 账户`
  1. 公司或组织名可以是：`Instagram`（推荐）， `Instagram 账户` 等，默认忽略大小写
  2. 验证码关键字可以是：`验证`（推荐）, `验证你`, `验证你的` 等，默认忽略大小写
  3. 验证码正则表达式： `(?<![0-9])[0-9]{6}(?![0-9])`, 其中：
    - `[0-9]` 表示：匹配 0 到 9 的数字
    - `[0-9]{6}` 表示： 匹配 6 位 0 到 9 的数字组成的字符串
    - `[0-9]{6}(?![0-9])` 表示： 匹配 6 位数字组成的字符串，且后面不能有数字
    - `(?<![0-9])[0-9]{6}(?![0-9])` 表示： 匹配 6 位数字组成的字符串，且其前后不能有数字
    - 更多正则表达式用法请参考 [Java正则表达式](http://www.runoob.com/java/java-regular-expressions.html) 或者自行网上搜索
- `[Steam] 动态口令为 x7jH98mx`
  1. 公司或组织名可以是： `Steam`（推荐），`[Steam]` 等，默认忽略大小写
  2. 验证码关键字可以是： `动态口令`（推荐），`口令` 等，默认忽略大小写
  3. 验证码正则表达式：`(?<![a-zA-Z0-9])[a-zA-Z0-9]{8}(?![a-zA-Z0-9])`