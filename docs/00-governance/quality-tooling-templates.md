# 质量工具配置模板（可复用）

以下片段用于新项目初始化或历史项目迁移时快速对齐质量门禁配置。

## 统一门禁命令

```bash
mvn clean verify
```

## 分档执行命令（新项目/历史项目）

```bash
# 历史项目（默认）：observe/warn 语义
mvn clean verify -Pharness-legacy

# 新项目：enforce 语义
mvn clean verify -Pharness-new
```

## pom.xml 关键片段

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.3.4</version>
  <relativePath/>
</parent>

<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
      <configuration>
        <skip>${spring-boot.repackage.skip}</skip>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-checkstyle-plugin</artifactId>
      <version>3.3.1</version>
      <executions>
        <execution>
          <phase>verify</phase>
          <goals><goal>check</goal></goals>
        </execution>
      </executions>
    </plugin>
    <plugin>
      <groupId>com.github.spotbugs</groupId>
      <artifactId>spotbugs-maven-plugin</artifactId>
      <version>4.8.6.6</version>
      <executions>
        <execution>
          <phase>verify</phase>
          <goals><goal>check</goal></goals>
        </execution>
      </executions>
    </plugin>
    <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.8.12</version>
      <executions>
        <execution>
          <id>jacoco-prepare-agent</id>
          <goals><goal>prepare-agent</goal></goals>
        </execution>
        <execution>
          <id>jacoco-report</id>
          <phase>verify</phase>
          <goals><goal>report</goal></goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

## ArchUnit 示例

```java
@AnalyzeClasses(packages = "com.example")
class LayerDependencyArchTest {
  @ArchTest
  static final ArchRule applicationNotDependOnInterfaces =
      noClasses().that().resideInAPackage("..application..")
          .should().dependOnClassesThat().resideInAPackage("..interfaces..");
}
```

## 覆盖率阈值建议

- 新项目：`jacoco.haltOnFailure=true`，`jacoco.coverage.minimum >= 0.80`
- 历史项目：先 `jacoco.haltOnFailure=false`，阈值从 `0.50` 逐步提升到 `0.80`

## 安全扫描（可选）

```bash
mvn clean verify -Psecurity-scan
```
