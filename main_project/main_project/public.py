import uuid
from flask import *
from database import *



public=Blueprint('public',__name__)


@public.route('/')
def home():  
 return render_template('home.html')


@public.route('/login',methods=['get','post'])
def login():
    if 'login' in request.form:
        uname=request.form['username']
        passw=request.form['password']
        
        qry="select * from login where username='%s' and password='%s'"%(uname,passw)
        res=select(qry)
        
        if res:
            session['lid']=res[0]['login_id']
            
            if res[0]['usertype']=='admin':
                return redirect(url_for('admin.adminhome'))
            if res[0]['usertype']=='doctor':
                qt="select * from doctor where login_id='%s'"%(session['lid'])
                ress=select(qt)
                session['did']=ress[0]['doctor_id']
                return redirect(url_for('doctor.doctorhome'))
            if res[0]['usertype']=='transportation':
                qtt="select * from transportation where login_id='%s'"%(session['lid'])
                ress_s=select(qtt)
                session['tid']=ress_s[0]['transportation_id']
                return redirect(url_for('transportation.transporthome'))
            if res[0]['usertype']=='homecare':
                qryy="select * from homecare where login_id='%s'"%(session['lid'])
                ress_ss=select(qryy)
                session['tid']=ress_ss[0]['homecare_id']
                return redirect(url_for('homecare.homecarehome'))
            

    return render_template("login.html")


@public.route('/doctor_reg',methods=['get','post'])
def doctor_reg(): 
    if 'reg' in request.form:
        dname=request.form['username']
        dpassw=request.form['password']
        name=request.form['name']
        qual=request.form['qual']
        spcl=request.form['Specialisation']
        phone=request.form['phone']
        email=request.form['email']
        license=request.form['License']
        

        qry="insert into login values(null,'%s','%s','pending')"%(dname,dpassw)
        loginid=insert(qry)
        d="insert into doctor values(null,'%s','%s','%s','%s','%s','%s','%s')"%(loginid,name,qual,spcl,phone,email,license)
        c=insert(d)

        print('///////////////',c)
        
    return render_template("doctorregister.html")

@public.route('/transportation_reg',methods=['get','post'])
def transportation_reg(): 
    if 'regg' in request.form:
        dname=request.form['username']
        dpassw=request.form['password']
        name=request.form['name']
        place=request.form['place']
        phone=request.form['phone']
        email=request.form['email']
        image=request.files['img']
        licenseproof=request.files['proof']
        vehtype=request.form['vehtype']
        vehno=request.form['vehno']

        path="static/"+str(uuid.uuid4())+image.filename
        image.save(path)

        path1="static/"+str(uuid.uuid4())+licenseproof.filename

        licenseproof.save(path1)
        
        qry="insert into login values(null,'%s','%s','transportation')"%(dname,dpassw)
        loginid=insert(qry)
        d="insert into transportation values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(loginid,name,place,phone,email,path,path1,vehtype,vehno)
        insert(d)
        
    return render_template("transportregister.html")

@public.route('/homecare_reg',methods=['get','post'])
def homecare_reg(): 
    if 'reggg' in request.form:
        dname=request.form['username']
        dpassw=request.form['password']
        fname=request.form['fname']
        lname=request.form['lname']
        place=request.form['place']
        phone=request.form['phone']
        email=request.form['email']
        image=request.files['image']
        idproof=request.files['idproof']
        gender=request.form['gender']
        

        path="static/"+str(uuid.uuid4())+image.filename
        image.save(path)

        path1="static/"+str(uuid.uuid4())+idproof.filename

        idproof.save(path1)
    
        qry="insert into login values(null,'%s','%s','homecare')"%(dname,dpassw)
        loginid=insert(qry)
        d="insert into homecare values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(loginid,fname,lname,place,phone,email,path,path1,gender)
        insert(d)
        
    return render_template("homecare_reg.html")




