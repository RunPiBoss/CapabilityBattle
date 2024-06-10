<template>
    <el-form label-position="top" label-width="100px" class="w-1/2" :model="code">
        <el-form-item>
            <div class="flex flex-row">
                <el-select v-model="selectValue" :change="changeMode()" class="m-2" placeholder="C++">
                    <el-option v-for="item in selectOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
                <el-select v-model="themeValue" class="m-2" :change="changeTheme()" placeholder="dark">
                    <el-option v-for="item in themeOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </div>
        </el-form-item>
        <el-form-item label="Your Code">
            <codemirror v-model="code" placeholder="Code gose here..." style="width: 100%;height: 430px;" :mode="mode"
                :smartIndent="true" :autoCloseBrackets="true" :spellcheck="spellcheck" :autofocus="autofocus"
                :indent-with-tab="indentWithTab" :tabSize="tabSize" :extensions="extensions" @ready="log('ready', $event)"
                @change="log('change', $event)" @focus="log('focus', $event)" @blur="useEditedCode" />
        </el-form-item>
    </el-form>
</template>

<script setup>
import { Codemirror } from "vue-codemirror";
import { java } from "@codemirror/lang-java";
import { cpp } from "@codemirror/lang-cpp";
import { oneDark } from "@codemirror/theme-one-dark";
import { ref, reactive, toRefs, defineExpose } from 'vue'
const code = ref("")//代码
const codeType = ref("cpp")
const log = console.log
defineExpose({
    code,codeType
})
const options = reactive({
    mode: "text/x-c++src",
    spellcheck: true,
    autofocus: true,
    indentWithTab: true,
    tabSize: 2,
    extensions: [cpp(), []], //传递给CodeMirror EditorState。创建({扩展})
});
const { mode, spellcheck, autofocus, indentWithTab, tabSize, extensions } = toRefs(options)
//代码语言选择
const selectValue = ref('')
const selectOptions = [
    {
        value: 'CPP',
        label: 'C++',
    },
    {
        value: 'Java',
        label: 'Java',
    },
]
const changeMode = () => {
    if (selectValue.value === "Java") {
        if (themeValue.value === 'white') {
            options.extensions = [java(), []];
        } else {
            options.extensions = [java(), [oneDark]];
        }
        options.mode = "text/x-java";
        codeType.value = "java"
    } else {
        if (themeValue.value === 'white') {
            options.extensions = [cpp(), []];
        } else {
            options.extensions = [cpp(), [oneDark]];
        }
        options.mode = "text/x-c++src";
        codeType.value = "cpp"
    }
}
const themeValue = ref('')
const themeOptions = [
    {
        value: 'white',
        label: 'white'
    },
    {
        value: 'oneDark',
        label: 'dark',
    },
]
const changeTheme = () => {
    if (themeValue.value === "white") {
        if (selectValue.value === 'C++') {
            options.extensions = [cpp(), []]
        } else {
            options.extensions = [java(), []]
        }
    } else {
        if (selectValue.value === 'C++') {
            options.extensions = [cpp(), [oneDark]]
        } else {
            options.extensions = [java(), [oneDark]]
        }
    }
}
</script>

<style lang="scss" scoped></style>