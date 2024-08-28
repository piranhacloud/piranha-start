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

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * The bean for the /start.xhtml page.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "startBean")
@RequestScoped
public class StartBean implements Serializable {

    /**
     * Stores the selected example.
     */
    private String example;

    /**
     * Stores the examples.
     */
    private SelectItem[] examples;

    /**
     * Stores the external context.
     */
    @Inject
    private ExternalContext externalContext;

    /**
     * Stores the faces context.
     */
    @Inject
    private FacesContext facesContext;

    /**
     * Stores our model.
     */
    private StartModel model = new StartModel();

    /**
     * Stores the project zip bean.
     */
    @Inject
    private ProjectZipBean projectZipBean;

    /**
     * Download action.
     */
    public void download() {
        externalContext.responseReset();
        externalContext.setResponseContentType("application/octet-stream");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=project.zip");
        try {
            OutputStream outputStream = externalContext.getResponseOutputStream();
            projectZipBean.write(model, outputStream);
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        facesContext.responseComplete();
    }

    /**
     * Handle changing profile.
     *
     * @param event the event.
     */
    public void changeProfile(AjaxBehaviorEvent event) throws AbortProcessingException {
        String value = event.getFacesContext().getExternalContext()
                .getRequestParameterMap().get("form:profile");

        if (value != null) {
            switch (value) {
                case "none" -> {
                    model.setPackaging("jar");
                    model.setRuntime("embedded");
                }
                
                case "coreprofile" -> {
                    model.setPackaging("war");
                    model.setRuntime("coreprofile");
                }

                case "webprofile" -> {
                    model.setPackaging("war");
                    model.setRuntime("webprofile");
                }
            }
        }
    }

    /**
     * Handle changing runtime.
     *
     * @param event the event.
     */
    public void changeRuntime(AjaxBehaviorEvent event) throws AbortProcessingException {
        String value = event.getFacesContext().getExternalContext()
                .getRequestParameterMap().get("form:runtime");

        if (value != null) {
            switch (value) {
                case "embedded" -> {
                    model.setPackaging("jar");
                    model.setProfile("none");
                }

                case "coreprofile" -> {
                    model.setPackaging("war");
                    model.setProfile("coreprofile");
                }
                
                case "server" -> {
                    model.setPackaging("war");
                    model.setProfile("none");
                }

                case "webprofile" -> {
                    model.setPackaging("war");
                    model.setProfile("webprofile");
                }
            }
        }
    }

    /**
     * Get the example.
     *
     * @return the example.
     */
    public String getExample() {
        return example;
    }

    /**
     * Get the examples.
     *
     * @return the examples.
     */
    public SelectItem[] getExamples() {
        return examples;
    }

    /**
     * Get the Java versions.
     *
     * @return the Java versions.
     */
    public SelectItem[] getJavaVersions() {
        return new SelectItem[]{
            new SelectItem("21", "21"),
            new SelectItem("22", "22")
        };
    }

    /**
     * Get the model.
     *
     * @return the model.
     */
    public StartModel getModel() {
        return model;
    }

    /**
     * Get the packagings.
     *
     * @return the packagings.
     */
    public SelectItem[] getPackagings() {
        return new SelectItem[]{
            new SelectItem("jar", "Jar"),
            new SelectItem("war", "War")
        };
    }

    /**
     * Get the stacks.
     *
     * @return the stacks.
     */
    public SelectItem[] getProfiles() {
        return new SelectItem[]{
            new SelectItem("none", "None"),
            new SelectItem("coreprofile", "Jakarta Core Profile 10"),
            new SelectItem("webprofile", "Jakarta Web Profile 10")
        };
    }

    /**
     * Get the runtimes.
     *
     * @return the runtimes.
     */
    public SelectItem[] getRuntimes() {
        return new SelectItem[]{
            new SelectItem("embedded", "Piranha Embedded"),
            new SelectItem("coreprofile", "Piranha Core Profile"),
            new SelectItem("server", "Piranha Server"),
            new SelectItem("webprofile", "Piranha Web Profile")
        };
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        examples = new SelectItem[2];
        examples[0] = new SelectItem(null, "Select an example", "Select an example", false, false, true);
        examples[1] = new SelectItem("webprofile-helloworld", "Hello World application");
    }

    /**
     * Set the example.
     *
     * @param example the example.
     */
    public void setExample(String example) {
        this.example = example;
    }

    /**
     * Set the examples.
     *
     * @param examples the examples.
     */
    public void setExamples(SelectItem[] examples) {
        this.examples = examples;
    }

    /**
     * Set the model.
     *
     * @param model the model.
     */
    public void setModel(StartModel model) {
        this.model = model;
    }
}
