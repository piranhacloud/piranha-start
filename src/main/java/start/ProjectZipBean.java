/*
 * Copyright (c) 2002-2023 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package start;

import jakarta.enterprise.context.Dependent;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The bean for writing out the project.zip file.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Dependent
public class ProjectZipBean {
    
    /**
     * Write pom.xml file.
     * 
     * @param model the model.
     * @param zipOutputStream the zip output stream.
     * @throws IOException when an I/O error occurs.
     */
    public void writePomXml(StartModel model, ZipOutputStream zipOutputStream) throws IOException {
        ZipEntry zipEntry = new ZipEntry("pom.xml");
        zipOutputStream.putNextEntry(zipEntry);
        String pomFile = 
                """
                <?xml version="1.0" encoding="UTF-8"?>
                
                <project xmlns="http://maven.apache.org/POM/4.0.0"
                         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>start</groupId>
                    <artifactId>start</artifactId>
                    <version>1-SNAPSHOT</version>
                    <packaging>war</packaging>
                    <properties>
                      <java.version>%s</java.version>
                    </properties>
                    <build>
                        <plugins>
                            <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-compiler-plugin</artifactId>
                                <version>3.10.1</version>
                                <configuration>
                                    <release>${java.version}</release>
                                </configuration>
                            </plugin>
                            <plugin>
                                <groupId>org.apache.maven.plugins</groupId>
                                <artifactId>maven-war-plugin</artifactId>
                                <version>3.3.2</version>
                                <configuration>
                                    <failOnMissingWebXml>false</failOnMissingWebXml>
                                </configuration>
                            </plugin>
                        </plugins>
                    </build>
                </project>
                """;
        pomFile = String.format(pomFile, model.getJavaVersion());
        zipOutputStream.write(pomFile.getBytes(Charset.forName("UTF-8")));
        zipOutputStream.closeEntry();
    }
    
    /**
     * Write the project.zip file.
     * 
     * @param model the model.
     * @param outputStream the output stream to write to.
     * @throws IOException when an I/O error occurs.
     */
    public void write(StartModel model, OutputStream outputStream) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        zipOutputStream.setComment("""
                                   --------------------------------------------------------------
                                   
                                    This project.zip file was generated by start.piranha.cloud,
                                    please consider donating to its project at:
                                   
                                           https://github.com/piranhacloud/piranha-start
                                   
                                   --------------------------------------------------------------
                                   """);
        writePomXml(model, zipOutputStream);
        zipOutputStream.finish();
    }
}