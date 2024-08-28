/*
 * Copyright (c) 2002-2024 Manorrock.com. All Rights Reserved.
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

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

/**
 * The start model.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class StartModel {
    
    /**
     * Stores the choices.
     */
    private List<String> choices;
    
    /**
     * Stores the Java version.
     */
    private String javaVersion = "21";
    
    /**
     * Stores the packaging.
     */
    private String packaging = "war";
    
    /**
     * Stores the profile.
     */
    private String profile = "webprofile";
    
    /**
     * Stores the runtime.
     */
    private String runtime = "webprofile";
    
    /**
     * Get the choices.
     * 
     * @return the choices.
     */
    public List<String> getChoices() {
        return choices;
    }
    
    /**
     * Get the Java version.
     *
     * @return the java version.
     */
    public String getJavaVersion() {
        return javaVersion;
    }
    
    /**
     * Get the packaging.
     * 
     * @return the packaging.
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * Get the profile.
     * 
     * @return the profile.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Get the runtime.
     * 
     * @return the runtime.
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * Set the choices.
     * 
     * @param choices the choices. 
     */
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    /**
     * Set the Java version.
     *
     * @param javaVersion the java version.
     */
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }
    
    /**
     * Set the packaging.
     * 
     * @param packaging the packaging.
     */
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * Set the profile.
     * 
     * @param profile the profile.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Set the runtime.
     * 
     * @param runtime the runtime.
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    
    /**
     * Helper method.
     * 
     * @param json the JSON string.
     * @return the model.
     */
    public static StartModel valueOf(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, StartModel.class);
    }
}
