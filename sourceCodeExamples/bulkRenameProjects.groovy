import hudson.model.*

disableChildren(Hudson.instance.items)

def disableChildren(items) {
  for (item in items) {
    if (item.class.canonicalName != 'com.cloudbees.hudson.plugins.folder.Folder') {
	if (( m = item.name =~ /^(Findur.OpenComponent)(\..*)$/)){
          println(item.name)
          println m.group(1) + " " + m.group(2)
          newname = m[0][1] + 's' + m.group(2)
          item.renameTo(newname)
      }
    } else {
	disableChildren(((com.cloudbees.hudson.plugins.folder.Folder) item).getItems())
    }
  }
}
