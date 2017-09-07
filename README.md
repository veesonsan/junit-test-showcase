## junit test showcase
为了简单,本工程使用spring boot框架

## 使用方法
1 修改application.yml中mongodb的host和port

2 运行JunitTestShowcaseApplication启动工程

3 运行junit test

4 maven命令运行mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test -Dmaven.test.failure.ignore=true sonar:sonar  -Dsonar.host.url=http://10.16.70.161:9000 -Dsonar.login=699f65e305e729989a4685636f02af05145d810d 将其中sonar的url和login的token修改为自己环境的值