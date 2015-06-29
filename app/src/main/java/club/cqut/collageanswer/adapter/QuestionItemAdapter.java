package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.Question;


/**
 * 问题的适配器
 * Created by fenghao on 2015/6/29.
 */
public class QuestionItemAdapter extends BaseAdapter{

    static class ViewHolder{
        ImageView headImage;
        TextView username;
        TextView answerNum;
        TextView questionTitle;
        TextView questionLabel;
    }

    private List<Question> list;
    private LayoutInflater inflater;

    public QuestionItemAdapter(Context context, List<Question> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO ImageLoader 加载图像

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_item_question_model, null);
            holder.headImage = (ImageView) convertView.findViewById(R.id.question_head);
            holder.username = (TextView) convertView.findViewById(R.id.question_name);
            holder.answerNum = (TextView) convertView.findViewById(R.id.answer_num);
            holder.questionTitle = (TextView) convertView.findViewById(R.id.question_title);
            holder.questionLabel = (TextView) convertView.findViewById(R.id.question_label);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
