package com.barosanu;

import com.barosanu.controller.services.FetchFoldersService;
import com.barosanu.controller.services.FolderUpdaterService;
import com.barosanu.model.EmailAccount;
import com.barosanu.model.EmailTreeItem;
import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private FolderUpdaterService folderUpdaterService;
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");
    private List<Folder> folderList = new ArrayList<Folder>();

    public EmailManager() {
        folderUpdaterService = new FolderUpdaterService(folderList);
        folderUpdaterService.start();
    }

    public List<Folder> getFolderList() {
        return this.folderList;
    }

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, folderList);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}
