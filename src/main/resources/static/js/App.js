var ProtoBuf = dcodeIO.ProtoBuf;

var builder = ProtoBuf.loadProtoFile("/protos/domain.proto");
Software = builder.build('Software');

function getDeveloper(name) {
    var software = new Software({"name": name});
    var buffer = software.encode();

    $.ajax({
        url: '/search',
        method: 'POST',
        responseType: 'arraybuffer',
        data: buffer.toArrayBuffer(),
        headers: {
            'Content-Type': 'application/x-protobuf'
        }
    });
}
