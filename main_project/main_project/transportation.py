import uuid
from flask import *
from database import *

transportation=Blueprint('transportation',__name__)

@transportation.route('/transporthome')
def transporthome():
    return render_template("transport_home.html")

@transportation.route("/transportationviewprofile",methods={'get','post'})
def transportationviewprofile():
    data={}
    qry="select * from  transportation where transportation_id='%s'"%(session['tid'])
    res=select(qry)
    data['view']=res
    
    if 'action' in request.args:
        
        id=request.args['id']
        action=request.args['action']
    else:
        action=None
    
    if action=='update':
        qt="select * from transportation  where transportation_id='%s'"%(session['tid'])
        ress=select(qt)
        data['up']=ress
         
    if 'update' in request.form:
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

        
        up="update transportation set fullname='%s',place='%s',phone='%s',email='%s',image='%s',license_proof='%s',type_of_vehicle='%s',vehicle_number='%s' where transportation_id='%s'"%(name,place,phone,email,path,path1,vehtype,vehno,id)
        update(up)
        
    return render_template("transport_viewprofile.html",data=data)



@transportation.route("/transportationviewrequest")
def transportationviewrequest():
    data={}
    qry="select * from  transportation_request"
    res=select(qry)
    data['view']=res
    
    if 'action' in request.args:
        action=request.args['action']
        id=request.args['id']
    else:
        action=None
        
    if action=='accept':
        qry1="update transportation_request set status='ACCEPT' where transportation_request_id='%s'"%(id)
        update(qry1) 
        return ("<script>alert('Accept successfull');window.location='/transportationviewrequest'</script>")

    if action=='reject':
        qry2="update transportation_request set status='Reject' where transportation_request_id='%s'"%(id)
        update(qry2)
    

        return ("<script>alert('Reject successfull');window.location='/transportationviewrequest'</script>")
    

    return render_template("transport_viewrequest.html",data=data)


@transportation.route("/transportationsendcomplaint",methods=['get','post'])
def transportationsendcomplaint():
    data={}
    
    qry="select * from  complaints where sender_id='%s'"%(session['lid'])
    res=select(qry)
    data['view']=res
    if "submit" in request.form:
        title=request.form['title']
        description=request.form['description']
        u="insert into complaints values(null,'%s','%s','%s','pending',curdate())"%(session['lid'],title,description)
        insert(u)
        return redirect(url_for('transportation.transportationsendcomplaint'))
    return render_template("transportion_sendcomplaint.html",data=data)

