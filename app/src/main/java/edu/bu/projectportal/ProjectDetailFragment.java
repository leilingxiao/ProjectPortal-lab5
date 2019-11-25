package edu.bu.projectportal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.bu.projectportal.database.ProjectDao;
import edu.bu.projectportal.database.ProjectPortalDBHelper;

public class ProjectDetailFragment extends Fragment {

    int projectId, nextProjectId;
    TextView titleTextView, summaryTextView, authorTextView, keywordsTextView,isFavoriteTextView, githubTextView, youtubeTextView;

    public ProjectDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        titleTextView = view.findViewById(R.id.projTitleTextViewId);
        summaryTextView = view.findViewById(R.id.projSummaryTextViewId);
        authorTextView = view.findViewById(R.id.projAuthorTextViewId);
        keywordsTextView = view.findViewById(R.id.projKeywordTextViewId);
        isFavoriteTextView = view.findViewById(R.id.projFavoriteTextViewId);

        githubTextView = view.findViewById(R.id.projLink1TextViewId);
        youtubeTextView = view.findViewById(R.id.projLink2TextViewId);


        if (getArguments()!= null) {
            projectId = getArguments().getInt("Projectid");
            nextProjectId = getArguments().getInt("NextProjectid");
        }

        Log.d("projectid", " " + projectId);
        setProject(projectId);

        return view;
    }

    public void setProject(int projId) {

        projectId = projId;
        ProjectDao projectDao = ProjectDao.getInstance(getContext());
        Project project = projectDao.getProjectById(projectId );

        titleTextView.setText(project.getId() + ": " + project.getTitle());
        summaryTextView.setText(project.getSummary());
        authorTextView.setText(project.getAuthor());
        keywordsTextView.setText(project.getKeywords());
        githubTextView.setText(project.getGithub());
        youtubeTextView.setText(project.getYoutube());
    }

    public int getProjectId(){
        return projectId;
    }


    public int getNextProjectId() {
        return nextProjectId;
    }


}
