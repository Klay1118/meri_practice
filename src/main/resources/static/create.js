function post(requestBody) {
  var begin = new Date();
  nlapiLogExecution('DEBUG', 'IN', (new Date()) + JSON.stringify(requestBody));
  var ret = [];
  var result = {};
  result.begin = begin;

  var field = requestBody.field_mapping;
  var list = requestBody.list_mapping;
  var data = requestBody.data;
  var recordType = requestBody.type;
  for(i = 0; i < data.length; i += 1) {
    ret.push(one_record(recordType, data[i], field,list));
  }
  nlapiLogExecution('DEBUG', 'OUT', (new Date()) + JSON.stringify(ret));

  result.data = ret;
  result.end = new Date();
  return result;
}


function one_record(type, data,  field,list) {
  var ret = {};
  ret.Status = 0;
  var begin = new Date();
  try {
    for(var f in field) {
      if(field[f] === 'externalid') {
        ret.guid = data[f]
      }
    }

    ret.result = create_record(type, data, field,list)
  } catch(e) {
    ret.Status = 1;
    ret.Message = e.message;
    ret.begin = begin;
    ret.end = new Date();
  }
  return ret;
}


function create_record(type, data , field,list) {
  var result = {};
  var stat = {};
  stat.inited = new Date();
  var rec = nlapiCreateRecord(type);
  for(var i in field) {
    if(data[i]){
      rec.setFieldValue(field[i], data[i]);
      result[field[i]] = data[i];
    }
  }

  for(var j in list) {
    if(data[j]){
      rec.setFieldText(list[j], data[j]);
      result[list[j]] = data[j];
    }
  }
  stat.prepared = new Date();
  result.id = nlapiSubmitRecord(rec);
  stat.submitted = new Date();
  result.stat = stat;
  return result;
}

