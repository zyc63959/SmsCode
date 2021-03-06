# SmsCode
SmsCode is non-xposed version of [XposedSmsCode](https://github.com/tianma8023/XposedSmsCode)。 It can recognize, parse SMS code and copy it to clipboard when a new message arrives, it can also auto-input SMS code.

[中文版说明](/README-CN.md)

# Screenshots
<img src="art/en/01.png" width="180"/><img src="art/en/02.png" width="180"/><img src="art/en/03.png" width="180"/><img src="art/en/04.png" width="180"/>

You can download the latest App from [Coolapk](https://www.coolapk.com/apk/com.github.tianma8023.smscode) or [releases](https://github.com/tianma8023/SmsCode/releases/).

# Attention
- **This module is suitable for AOSP ROM, it may not work well on other 3rd-party Rom.**
- **Compatibility: Requires Android 5.0+ (api level ≥ 21).**
- **Read the FAQ in app first if you encountered any problems.**

# Features
- Copy verification code to clipboard when a new message arrives.
- Show toast when a SMS verification code is copied.
- Show notification when code SMS parsed.
- Custom keywords about verification code message (regular expressions allowed).
- Custom SMS code match rules.
- Mark verification code SMS as read (experimental).
- Delete verification SMS when it been extracted successfully (experimental).
- Block code SMS message notification (experimental).
- Auto-input SMS code.
- Various theme color to choose.

# Update Logs
[Update logs](/LOG-EN.md)

# Thanks
- [drakeet SmsCodeHelper](https://github.com/drakeet/SmsCodeHelper)
- [zhidao8 SmsCodeHelper](https://github.com/zhidao8/SmsCodeHelper)
- [AndPermission](https://github.com/yanzhenjie/AndPermission)
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Material Dialogs](https://github.com/afollestad/material-dialogs)
- [Android Shell](https://github.com/jaredrummler/AndroidShell)
- [logback-android](https://github.com/tony19/logback-android)
- [Bugly](https://bugly.qq.com)
- [EventBus](https://github.com/greenrobot/EventBus)
- [GreenDao](https://github.com/greenrobot/greenDAO)
- [GreenDaoUpgradeHelper](https://github.com/yuweiguocn/GreenDaoUpgradeHelper)
- [Gson](https://github.com/google/gson)
- [BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

# License
All code is licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.txt) 