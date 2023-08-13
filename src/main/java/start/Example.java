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

import java.io.Serializable;

/**
 * An example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class Example implements Serializable {
    
    /**
     * Stores the description.
     */
    private String description;
    
    /**
     * Stores the disabled flag.
     */
    private boolean disabled;
    
    /**
     * Stores the selectable flag.
     */
    private boolean selectable;
    
    /**
     * Stores the selected flag.
     */
    private boolean selected;

    /**
     * Default constructor.
     */
    public Example() {
    }
    
    /**
     * Constructor.
     * 
     * @param description the description. 
     */
    public Example(String description) {
        this.description = description;
    }
    
    /**
     * Constructor.
     * 
     * @param description the description. 
     * @param selectable the selectable flag.
     */
    public Example(String description, boolean selectable) {
        this.description = description;
        this.selectable = selectable;
    }

    /**
     * Get the description.
     * 
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Is the example disabled.
     * 
     * @return true if it is, false otherwise.
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Is the example selectable.
     * 
     * @return true if it is, false otherwise.
     */
    public boolean isSelectable() {
        return selectable;
    }   
    
    /**
     * Is the example selected.
     * 
     * @return true if the example is selected, false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set the description.
     * 
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the disabled flag.
     * 
     * @param disabled the disabled flag.
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Set the selectable flag.
     * 
     * @param selectable the selectable flag.
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * Set the selected flag.
     * 
     * @param selected the selected flag.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
