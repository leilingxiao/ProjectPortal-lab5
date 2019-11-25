package edu.bu.projectportal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.bu.projectportal.Project;



public class ProjectDao {
    public static ProjectDao instance;

    public ProjectPortalDBHelper projectPortalDBHelper;
    public SQLiteDatabase mReadableDB, mWritableDB;


    public ProjectDao(Context context){
        projectPortalDBHelper = new ProjectPortalDBHelper(context);
    }

    public void openDb(){
        mReadableDB = projectPortalDBHelper.getReadableDatabase();
        mWritableDB = projectPortalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        mReadableDB.close();
        mWritableDB.close();
    }

    public static ProjectDao getInstance(Context context){
        if (instance == null)
            instance = new ProjectDao(context);
        return instance;
    }

    public long insertProject(Project project) {

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR,
                project.getAuthor());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD,
                project.getKeywords());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE,
                project.isFavorite());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1,
                project.getGithub());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2,
                project.getYoutube());
        return mWritableDB.insert(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                null, projectValue);
    }

    public long delectProjectById(int projectId) {
        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId+""};

        return mWritableDB.delete(ProjectPortalDBContract.ProjectContract.TABLE_NAME ,
                selection,selectionArgs);

    }

    public long updateProjectById(int projectId) {
        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId+""};

        return mWritableDB.delete(ProjectPortalDBContract.ProjectContract.TABLE_NAME ,
                selection,selectionArgs);

    }


    public List<Project> getAllProject() {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2

        };

        Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection,
                null, null, null, null,null);

        List<Project> projects = new ArrayList<Project>();

        while(cursor.moveToNext()) {
            int projectId = cursor.getInt(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID));
            String projectTitle = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
            String projectSum = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
            String projectAuthor = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR));
            String projectKeyword = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD));
            boolean projectisFavorate = cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE)==1;
            String projectLink1 = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1));
            String projectLink2 = cursor.getString(cursor.getColumnIndex(
                    ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2));

            projects.add(new Project(projectId, projectTitle,projectSum,projectAuthor,projectKeyword,projectisFavorate,projectLink1,projectLink2));
        }

        cursor.close();

        return projects;
    }

    public Project getProjectById(int projectId) {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {Integer.toString(projectId)};

        Cursor cursor = mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);

        cursor.moveToFirst();

        String projectTitle = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title));
        String projectSum = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY));
        String projectAuthor = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR));
        String projectKeyword = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD));
        boolean projectisFavorate = cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE) == 1;
        String projectLink1 = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1));
        String projectLink2 = cursor.getString(cursor.getColumnIndex(
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2));

        Project project = new Project(projectId, projectTitle,projectSum, projectAuthor, projectKeyword, projectisFavorate, projectLink1, projectLink2);
        cursor.close();

        return project;
    }

    public Cursor getProjectByTitle(String projectTitle) {

        String[] projection = {ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1,
                ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2};

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title + "LIKE ?";
        String[] selectionArgs = {projectTitle};

        return mReadableDB.query(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null,null);
    }


    public long updateProjectById(SQLiteDatabase sqLiteDatabase, Project project, int projectId) {

        String selection = ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ID + "=?";
        String[] selectionArgs = {projectId +""};

        ContentValues projectValue = new ContentValues();
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_Title,
                project.getTitle());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_SUMMARY,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_AUTHOR,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_KEYWORD,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_ISFAVORATE,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK1,
                project.getSummary());
        projectValue.put(ProjectPortalDBContract.ProjectContract.COLUMN_PROJECT_LINK2,
                project.getSummary());
        return mWritableDB.update(ProjectPortalDBContract.ProjectContract.TABLE_NAME,
                projectValue, selection, selectionArgs);

    }
}
