package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp{
  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> filesList = new ArrayList<>();
    try (Stream<Path> walk = Files.walk(Paths.get(rootDir))) {
      filesList = walk.filter(Files::isRegularFile)
          .map(x -> x.toFile()).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("Error listing files in directory", e);
    }
    return filesList;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> lines = new ArrayList<>();
    try (Stream<String> stream = Files.lines(Paths.get(inputFile.toURI()))) {
      lines = stream.collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("Error reading lines from File", e);
    }
    return lines;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    FileWriter writer = new FileWriter(getOutFile());
    lines.stream().forEach(line -> {
        writer.write(line + System.lineSeparator());
    });
    writer.close();
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    matchedLines = listFiles(getRootPath()).stream()
        .flatMap(file -> readLines(file).stream()
            .filter(line -> containsPattern(line))).collect(Collectors.toList());
    writeToFile(matchedLines);
  }
  public static void main(String[] args) {
    BasicConfigurator.configure();
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error("Error in pattern matching", ex);
    }
  }
}
