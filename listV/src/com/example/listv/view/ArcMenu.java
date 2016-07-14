package com.example.listv.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.listv.R;



public class ArcMenu extends ViewGroup implements OnClickListener{

	private static final int POS_LEFT_TOP =0;
	private static final int POS_LEFT_BOTTOM =1;
	private static final int POS_RIGHT_TOP =2;
	private static final int POS_RIGHT_BOTTOM =3;
	
	
	/**
	 * �˵�λ��
	 */
	private Position mPosition = Position.RIGHT_BOTTOM;//λ�ã�Ĭ��Ϊright_bottom
	
	public enum Position{//����˵�λ�ã�ö������
		LEFT_TOP,LEFT_BOTTOM,RIGHT_TOP,RIGHT_BOTTOM
	}
	
	private int mRadius;//�˵��뾶
	/**
	 * �˵�״̬���򿪻�ر�
	 */
	private Status mCurrentStatus = Status.CLOSE;
	
	public enum Status{//�˵�״̬
		OPEN,CLOSE
	}
	
	private View mCButton;//�˵�����ť
	
	private OnMenuItemClikListener mMenuItemClikListener;

	/**
	 * ����Ӳ˵���Ļص��ӿ�
	 * @author Administrator
	 *
	 */
	//����һ���ӿڣ����û����Իص�
	public interface OnMenuItemClikListener
	{
		void onClick(View view,int pos);//�����д���һ������,����mCButtom��mRadius
		
	}
	public void setOnMenuItemClikListener(
			OnMenuItemClikListener mMenuItemClikListener) {
		this.mMenuItemClikListener = mMenuItemClikListener;
	}

	
	//��һ������������õڶ������������this(context,null)
	//�ڶ������õ���������ֵȫ��������������������
	//����Ϊ�˷�ֹ�û�ͨ������new���ǵ�ArcMenu
	public ArcMenu(Context context) {
		this(context,null);

	}
	
	public ArcMenu(Context context, AttributeSet attrs) {
		this(context, attrs,0);

	}

	public ArcMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
				getResources().getDisplayMetrics());//����ʱ��mradius��Ĭ��ֵ
		//��ȡ�Զ������Ե�ֵ
		//λ�õĸ�ֵ
	 /**
	  * ���Զ���view�Ĵ����������Զ������ԣ��޸Ĺ��캯��
		contextͨ������obtainStyledAttributes��������ȡһ��TypeArray��Ȼ���ɸ�TypeArray�������Խ�������
		obtainStyledAttributes������������������õ�����һ��������obtainStyledAttributes(int[] attrs)�������ֱ��styleable�л��
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
		���ý�������ص���recycle()������������ε��趨����´ε�ʹ�����Ӱ��  
	  */
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.ArcMenu,
				defStyle,0);
		
		int pos = a.getInt(R.styleable.ArcMenu_position, POS_RIGHT_BOTTOM);
		 switch (pos) {
		case POS_LEFT_TOP:
			mPosition = Position.LEFT_TOP;
			break;
		case POS_LEFT_BOTTOM:
			mPosition = Position.LEFT_BOTTOM;
			break;

		case POS_RIGHT_TOP:
			mPosition = Position.RIGHT_TOP;
			break;

		case POS_RIGHT_BOTTOM:
			mPosition = Position.RIGHT_BOTTOM;
			break;

		}
		 
		mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius,
				 TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
						getResources().getDisplayMetrics()));
		//Ϊ�˼���mPosition,mRadius�Ƿ�õ���ֵ
		Log.e("TAG", "position =" + mPosition + ",radius =" + mRadius);
		a.recycle();//�ͷ�TypeArray,����Ӱ����һ��ʹ��
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int count = getChildCount();//�õ�childview������
		for (int i = 0; i < count; i++) {
			//getChildAt(i)ȡ����Ӧi��ChildView
			//����child,���������˿�Ⱥ͸߶�
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if(changed)//��������ı�
		{
			layoutCButton();
			
			
			int count = getChildCount();
			
			for (int i = 0; i < count - 1; i++) {
				View child = getChildAt(i+1);
				
				child.setVisibility(View.GONE);//�����Ӳ˵�һ��ʼ�����ص�
				//count-2����Ϊ��count��ʾ�˵��������������˵�
				int cl = (int) (mRadius * Math.sin(Math.PI/2/(count -2)*i));
				int ct = (int) (mRadius * Math.cos(Math.PI/2/(count-2)*i));
				/**
				 * һ��ʼ��getMeasuredHeight()��getMeasuredWidthд����getMinimumWidth()��getMinimumHeight()
				 * ���³����޷�ִ�У��´�ר�ĵ�
				 */
				int cWidth = child.getMeasuredWidth();
				int cHeight = child.getMeasuredHeight();
				//����˵�λ���ڵײ����£�����
				if(mPosition == Position.LEFT_BOTTOM || mPosition == Position.RIGHT_BOTTOM)
				{
					ct = getMeasuredHeight() - ct - cHeight;
				}
				//����˵�λ�������ϣ�����
				if(mPosition == Position.RIGHT_TOP || mPosition == Position.RIGHT_BOTTOM)
				{
					cl = getMeasuredWidth() - cl - cWidth;
				}
				
				//����λ��
				child.layout(cl, ct, cl+cWidth, ct+cHeight);
			}
			
		}
	}

/**
 * ȷ������ť��λ��
 */
	private void layoutCButton() {
		mCButton = getChildAt(0);
		mCButton.setOnClickListener(this);
		
		int l = 0; //��ʾ���
		int t = 0;//��ʾ�߶�
		int width = mCButton.getMeasuredWidth();
		int height = mCButton.getMeasuredHeight();
		
		switch (mPosition) {
		//l,t��Ҫ������ˣ���ȻͼƬ�޷�������ʾ��������
		case LEFT_TOP:
			l = 0;
			t = 0;
			break;
		case LEFT_BOTTOM:
			l = 0;
			t = getMeasuredHeight() - height;
			break;
		case RIGHT_TOP:
			l = getMeasuredWidth() - width;
			t = 0;
			break;
		case RIGHT_BOTTOM:
			t = getMeasuredHeight() - height;
			l = getMeasuredWidth() - width;
			break;
		}
		//��ťλ��ȷ����l��tΪ��ť������l+width��t+widthΪ��ť�ײ�
		mCButton.layout(l, t, l+width, t+width);
		
	}


@Override
public void onClick(View v) {
	rotateCButton(v,0f,360f,300);//���˵���תʵ�ֵķ���,300Ϊʱ��
	toggleMenu(300);
}
/**
 * �л��˵�
 */
public void toggleMenu(int duration) {
	//ΪmenuItem���ƽ�ƶ�������ת����
	int count = getChildCount();

	for (int i = 0; i < count-1; i++) {
		final View childView = getChildAt(i+1);
		/**
		 * �����Ӳ˵��رջ��Ǵ򿪣���Ҫ��ʾ��������ʾ��������̣�
		 * �����ö�����������������������Ӧ������
		 */
		childView.setVisibility(View.VISIBLE);
		/**
		 * Ϊ��ʹ��ť���Ե��������ʹ�õĴ�ͳ����������ƶ���ԭ����λ�ã���ť�Ͳ��ɵ����
		 * ���԰�ť����ʼλ�ò��ܸı䣬�������Ǿ�����x,y��ĸ�ֵ�����������ļ���
		 * ��Ϊ��ť����������
		 */
		//�Ӳ˵�����ʼλ�þ��Ǹ���cl��ct,��Ȼֻ�Ƕ������������ģ��ĸ���ͬ�Ľǣ�x,y�仯��һ��
		int cl = (int) (mRadius * Math.sin(Math.PI/2/(count -2)*i));
		int ct = (int) (mRadius * Math.cos(Math.PI/2/(count-2)*i));
		
		int xflag = 1;
		int yflag = 1;
		//�������£��Ӳ˵���ʵ���ջصģ�Ҳ����x,y�仯�Ǹ�ֵ
		if(mPosition == Position.LEFT_TOP || mPosition == Position.LEFT_BOTTOM)
		{
			xflag = -1;
		}
		//ͬ��
		if(mPosition == Position.LEFT_TOP || mPosition == Position.RIGHT_TOP)
		{
			yflag = -1;
		}
		
		AnimationSet animset = new AnimationSet(true);
		
		Animation tranAnim = null;//ƽ�ƶ���
		//�ж��Ӳ˵��ǹرջ��Ǵ򿪣�ʹ�ò�ͬx��yֵ��ƽ�ƶ���
		//��ǰ�ǹرյģ�to open
		if(mCurrentStatus == Status.CLOSE)
		{
			//��������Ϊ����ʼ����������ʼ���������ƶ�ֵ
			tranAnim = new TranslateAnimation(xflag*cl, 0, yflag*ct, 0);
			childView.setClickable(true);//���Ե����
			childView.setFocusable(true);//��ý���
		}else{//��ǰ�Ǵ򿪵�, to close
			tranAnim = new TranslateAnimation(0, xflag*cl, 0, yflag*ct);
			childView.setClickable(false);
			childView.setFocusable(false);
		}
		tranAnim.setFillAfter(true);//ƽ�ƺ�ͣ����
		tranAnim.setDuration(duration);//ʱ�䣬�ô����ֵ
		//���ö��������¼������ڵ���������ʱ����ͼ������
		tranAnim.setStartOffset((i*100)/count);//����i�Ĳ�ͬ���õ���ͬ��ƽ���ٶ�
		
		tranAnim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				//�������������Ҳ�����Ӳ˵�Ҫ�ر��ˣ���������
				if(mCurrentStatus == Status.CLOSE)
				{
					childView.setVisibility(View.GONE);
				}
			}
		});
	
		//��ת����
		RotateAnimation rotateAnim = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
			Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(duration);//ʱ������
		rotateAnim.setFillAfter(true);//���������ֹͣ
		
		
		//AnimationSet animset = new AnimationSet(true); animset��������
		//��������ת���������ƶ�,��ͬ˳����в�ͬЧ��
		animset.addAnimation(rotateAnim);
		animset.addAnimation(tranAnim);
		
		childView.startAnimation(animset);//���ض�����childView��ͼ��
		
		//������Ӳ˵��Ŵ󶯻�
		final int pos = i+1;//ÿ���Ӳ˵�
		childView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mMenuItemClikListener!= null)//����д��һ���ص�����������Ϊ�գ����лص�
				{
					mMenuItemClikListener.onClick(childView, pos);
				}
				/**
				 * ʵ�ֶ���
				 * ��pos���룬��Ϊpos�ǷŴ�ͼƬ��������ˣ�������ͼƬ��С
				 */
				menuItemAnim(pos-1);
				changeStatus();//�л��˵�״̬
				//ÿ��ִ�к�Ҫ�л��˵�״̬
			}
		});

	}
	//Ҫ��forѭ����
	changeStatus();//�л��˵�״̬
	//ÿ��ִ�к�Ҫ�л��˵�״̬
}

/**
 * ���menuItem�ĵ������
 * @param pos
 */
private void menuItemAnim(int pos) {
	for (int i = 0; i < getChildCount()-1; i++) {//getChildCount�������˵�
		View childView = getChildAt(i+1);
		if(i == pos)//ѡ�е�view
		{
			childView.startAnimation(scaleBigAnim(300));
		}else{
			childView.startAnimation(scaleSmallAnim(300));
		}
		//��Ϊ����ˣ��Ӳ˵���ʧ������Щ���Զ�Ϊfalse��
		childView.setClickable(false);
		childView.setFocusable(false);
	}
	
}


/**
 * Ϊ��ǰ�������item���ñ���͸���Ƚ��͵Ķ���
 * @param duration
 * @return
 */
private Animation scaleBigAnim(int duration) {

	//���������Ҫ��AnimationSet����ͬ��add����Ӷ������ټ��ؽ�childview
	AnimationSet animationSet = new AnimationSet(true);
	//����һ����ָ�Ŵ�4��
	ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f, 
			Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	
	AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);//͸�ܶ�
	
	animationSet.addAnimation(scaleAnim);
	animationSet.addAnimation(alphaAnim);
	
	animationSet.setDuration(duration);
	animationSet.setFillAfter(true);
	
	return animationSet;
}


private Animation scaleSmallAnim(int duration) {
	//���������Ҫ��AnimationSet����ͬ��add����Ӷ������ټ��ؽ�childview
		AnimationSet animationSet = new AnimationSet(true);
		//����һ����ָ�Ŵ�4��
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);//͸�ܶ�	
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);
		
		animationSet.setDuration(duration);
		animationSet.setFillAfter(true);
		
		return animationSet;
	
	
	
}


//�л��˵�״̬
private void changeStatus() {
	mCurrentStatus = (mCurrentStatus == Status.CLOSE?Status.OPEN:Status.CLOSE);
	
}
//״̬���жϣ�����MainActivity.java�е�listView�»�ʱ�жϣ��ڽ�������������ȥ
public boolean isOpen()
{
	return mCurrentStatus == Status.OPEN;
}
//start	��ʼ�Ƕȣ�end	�����Ƕȣ�i �������̵�ʱ��
private void rotateCButton(View v, float start, float end, int duration) {
	//����һ��Ϊ��ʼ����������Ĳ���	��ͼƬ������ת
	RotateAnimation anim = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF, 0.5f,
			Animation.RELATIVE_TO_SELF, 0.5f);
	anim.setDuration(duration);
	anim.setDuration(duration);//??
	anim.setFillAfter(true);//ת���ͣ����
	v.startAnimation(anim);//���ض�����view
}



}
