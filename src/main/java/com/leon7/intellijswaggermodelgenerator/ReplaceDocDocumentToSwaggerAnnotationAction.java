package com.leon7.intellijswaggermodelgenerator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang3.StringUtils;

import java.awt.datatransfer.StringSelection;
import java.util.Objects;

public class ReplaceDocDocumentToSwaggerAnnotationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformCoreDataKeys.EDITOR);
        if (Objects.isNull(editor)) {
            return;
        }
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (StringUtils.isBlank(selectedText)) {
            Messages.showWarningDialog("Nothing selected!", "SwaggerAnnotationAction");
            return;
        }
        String result = selectedText
                .replaceAll("/\\*\\*\\n     \\* ", "@ApiModelProperty(\"")
                .replaceAll("\\n     \\*/", "\")");
        CopyPasteManager.getInstance().setContents(new StringSelection(result));
        Messages.showInfoMessage("Has been copied to clipboard.", "SwaggerAnnotationAction");
    }
}
