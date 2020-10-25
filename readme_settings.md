### Settings
- 新しい機能を使いたいときに `symbol cannot resolve` のようなエラーが出る → `build.gradle` の `sourceCompatibility` のバージョンをその機能を提供しているバージョンに変える.
- これに合わせて jdk も変えないといけないので以下の手順で変更する. (対象の jdk がインストールされていることが前提)
  - `Could not target platform: 'Java SE 10' using tool chain: 'JDK 8 (1.8)'.`のようなエラーが出る.
```txt
This is what worked for me (Intellij Idea 2018.1.2):

1) Navigate to: File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle

2) Gradle JVM: change to version 1.8

3) Re-run the gradle task
```
