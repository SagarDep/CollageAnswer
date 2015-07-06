package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.util.comp.DateUtil;


/**
 * 问题的适配器
 * Created by fenghao on 2015/6/29.
 */
public class QuestionItemAdapter extends BaseAdapter{

    static class ViewHolder{
        ImageView headImage;
        TextView username;
        TextView readNum;
        TextView questionTitle;
        TextView questionLabel;
        TextView showTime;
    }
    private Context context;
    public List<Question> list = new ArrayList<>();
    private LayoutInflater inflater;

    // DisplayImageOptions的初始化
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.bootstrap_1)
            .showImageOnLoading(R.mipmap.bootstrap_1)
            .showImageOnFail(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public QuestionItemAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    /**
     * 得到最新数据往list里面添加
     */
    public void addNewQuestion(List<Question> questions){
        list.clear();
        list.addAll( questions);
    }

    /**
     * 加载更多的时候往list的最后加数据
     * @param questions
     */
    public void addOldQuestion(List<Question> questions){
        list.addAll(list.size(), questions);
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_item_question_model, null);
            holder.headImage = (ImageView) convertView.findViewById(R.id.question_head);
            holder.username = (TextView) convertView.findViewById(R.id.question_name);
            holder.readNum = (TextView) convertView.findViewById(R.id.read_num);
            holder.questionTitle = (TextView) convertView.findViewById(R.id.question_title);
            holder.questionLabel = (TextView) convertView.findViewById(R.id.question_label);
            holder.showTime = (TextView) convertView.findViewById(R.id.show_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.username.setText(list.get(position).getUserName());
        holder.readNum.setText(list.get(position).getReadNum());
        holder.questionTitle.setText(list.get(position).getTitle());
        holder.showTime.setText(DateUtil.formatSimple(list.get(position).getTime()));
        holder.questionLabel.setText("");
        //处理标签
        if( list.get(position).getLabel() != null && !list.get(position).getLabel().equals("")){
            String[] split = list.get(position).getLabel().split(",");
            for (int i = 0 ; i < split.length ; i++){
                String str = split[i] + "  ";
                int bstart = str.lastIndexOf("  ");
                int bend = bstart + "  ".length();
                SpannableStringBuilder style=new SpannableStringBuilder(str);
                style.setSpan(new BackgroundColorSpan(Color.rgb(238,238,238)),bstart,bend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.questionLabel.append(style);
            }
        }else{
            holder.questionLabel.setText("");
        }





        //imageLoader加载图像
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(list.get(position).getHeadImage(), holder.headImage, options);
        return convertView;
    }
}
