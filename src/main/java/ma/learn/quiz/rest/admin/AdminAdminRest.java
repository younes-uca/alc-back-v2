package ma.learn.quiz.rest.admin;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import ma.learn.quiz.bean.Admin;
import ma.learn.quiz.service.AdminService;
import miniApp.migration.DataBaseMigration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin/admin")
@CrossOrigin("http://localhost:4200")
public class AdminAdminRest {
    @GetMapping("/numero/{numro}")
    public Admin findByNumero(@PathVariable String ref) {
        return adminService.findByNumero(ref);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable String ref) {
        return adminService.deleteByNumero(ref);
    }


    @GetMapping("/")
    public List<Admin> findAll() {
        return adminService.findAll();
    }


    @PostMapping("/")
    public int save(@RequestBody Admin admin) {
        System.out.println(admin.getNom());
        System.out.println(admin.getUsername());
        return adminService.save(admin);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return adminService.findByCritere(login, password);
    }

    @Autowired
    private DataBaseMigration dataBaseMigration;


    @GetMapping("/app")
    public void start() throws Exception {
        dataBaseMigration.htmlimagetext();
    }

    @GetMapping("/app/saveimage")
    public void createFile() throws Exception {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();


        Credential cred = this.flow.loadCredential(USER_IDENTIFIER_KEY);

        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred).setApplicationName(APP_NAME).build();
        com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
        com.google.api.services.drive.model.File folder = new com.google.api.services.drive.model.File();
        com.google.api.services.drive.model.File folder2 = new com.google.api.services.drive.model.File();

// -------------------------------- FOLDER ONE ----------------------------------------
        folder2.setName("folder1");
        folder2.setMimeType("application/vnd.google-apps.folder");
        com.google.api.services.drive.model.File uploadedFolder2 = drive.files().create(folder2).setFields("id").execute();
        // -------------------------------- FOLDER TWO ----------------------------------------

        folder.setName("folder");
        folder.setMimeType("application/vnd.google-apps.folder");
        folder.setParents(Arrays.asList(uploadedFolder2.getId()));
        com.google.api.services.drive.model.File uploadedFolder = drive.files().create(folder).setFields("id").execute();


        file.setName("1.jpg");
        file.setParents(Arrays.asList(uploadedFolder.getId()));
        FileContent content = new FileContent("image/jpeg", new java.io.File("C:\\KamImages\\images\\parcours2\\Lesson\\DISCUSSION1\\1.jpg"));
        com.google.api.services.drive.model.File uploadedFile = drive.files().create(file, content).setFields("id").execute();
        String fileReference = String.format("{fileID: '%s'}", uploadedFile.getId());
        System.out.println("=========================================================");
        System.out.println(fileReference);
    }




    @GetMapping("/googlesignin")
    public void googleSignIn(HttpServletResponse response) throws Exception {
        this.generateFlow();
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectUrl = url.setRedirectUri(CALLBACK_URL).setAccessType("offline").build();
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/oauth")
    public void saveAuthorizationCode(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        if (code != null) {
            saveToken(code);
        }
    }

    public void generateFlow() throws Exception {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(gdSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile())).build();
    }

    private void saveToken(String code) throws Exception {
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(CALLBACK_URL).execute();
        flow.createAndStoreCredential(response, USER_IDENTIFIER_KEY);
    }


    private static final String APP_NAME = "alc-project-drive-img";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String USER_IDENTIFIER_KEY = "526671645204-pefjgst2s07uo5k8v0srh75pc7i28e8p.apps.googleusercontent.com";
    private GoogleAuthorizationCodeFlow flow;

    @Autowired
    private AdminService adminService;

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URL;
    @Value("${google.secret.key.path}")
    private Resource gdSecretKeys;
    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;
}
