// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.axellience.vuegwtplugin.model

import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiType

interface VueContainer {
    val data: List<VueDataProperty>
    val computed: List<VueComputedProperty>
    val methods: List<VueMethod>
    val props: List<VueInputProperty>
    val emits: List<VueEmitCall>
    val slots: List<VueSlot>

    val element: String? get() = null
    val extends: List<VueContainer>
    val delimiters: Pair<String, String>? get() = null
    val model: VueModelDirectiveProperties
}

class VueModelDirectiveProperties(
        val prop: String = DEFAULT_PROP,
        val event: String = DEFAULT_EVENT
) {
    companion object {
        const val DEFAULT_PROP = "value"
        const val DEFAULT_EVENT = "input"
    }
}

interface VueNamedSymbol {
    val name: String
    val source: PsiElement? get() = null
}

interface VueSlot : VueNamedSymbol {
    val scope: PsiType? get() = null
    val pattern: Regex? get() = null
}

interface VueEmitCall : VueNamedSymbol {
    val eventType: PsiType? get() = null
}

interface VueProperty : VueNamedSymbol {
    val type: PsiType? get() = null
}

interface VueInputProperty : VueProperty {
    val required: Boolean
    val defaultValue: String? get() = null
}

interface VueDataProperty : VueProperty

interface VueComputedProperty : VueProperty

interface VueMethod : VueNamedSymbol
